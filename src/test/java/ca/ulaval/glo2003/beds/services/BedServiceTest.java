package ca.ulaval.glo2003.beds.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.BedResponseBuilder.aBedResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.converters.BedConverter;
import ca.ulaval.glo2003.beds.converters.BedNumberConverter;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQuery;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private static BedService bedService;
  private static BedFactory bedFactory = mock(BedFactory.class);
  private static BedQueryFactory bedQueryFactory = mock(BedQueryFactory.class);
  private static BedConverter bedConverter = mock(BedConverter.class);
  private static BedNumberConverter bedNumberConverter = mock(BedNumberConverter.class);
  private static BedRepository bedRepository = mock(BedRepository.class);
  private static BedStarsCalculator bedStarsCalculator = mock(BedStarsCalculator.class);
  private static LocationService locationService = mock(LocationService.class);

  private UUID bedNumber = createBedNumber();
  private Location origin = createLocation();
  private Location validatedLocation = createLocation();
  private Bed bed = aBed().withBedNumber(bedNumber).build();
  private Bed otherBed = aBed().build();
  private BedRequest bedRequest = aBedRequest().build();
  private int stars = 4;
  private int otherStars = 2;
  private BedResponse bedResponse = aBedResponse().withStars(stars).build();
  private BedResponse otherBedResponse = aBedResponse().withStars(otherStars).build();
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpService() {
    bedService =
        new BedService(
            bedFactory,
            bedQueryFactory,
            bedConverter,
            bedNumberConverter,
            bedRepository,
            bedStarsCalculator,
            locationService);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedConverter.fromRequest(bedRequest)).thenReturn(bed);
    when(locationService.getLocation(bedRequest.getZipCode())).thenReturn(validatedLocation);
    when(bedFactory.create(bed, validatedLocation)).thenReturn(bed);
  }

  @BeforeEach
  public void setUpMocksForGetAll() {
    InMemoryBedQuery bedQuery = mock(InMemoryBedQuery.class);
    when(bedQueryFactory.create(params)).thenReturn(bedQuery);
    when(bedRepository.getAll(bedQuery)).thenReturn(Arrays.asList(bed, otherBed));
    when(bedStarsCalculator.calculateStars(bed)).thenReturn(stars);
    when(bedStarsCalculator.calculateStars(otherBed)).thenReturn(otherStars);
    when(locationService.getLocation(origin.getZipCode().getValue())).thenReturn(validatedLocation);
    when(bedConverter.toResponseWithNumber(bed, stars)).thenReturn(bedResponse);
    when(bedConverter.toResponseWithNumber(otherBed, otherStars)).thenReturn(otherBedResponse);
    when(bedNumberConverter.toString(bedNumber)).thenReturn(bedNumber.toString());
  }

  @BeforeEach
  public void setUpMocksForGetByNumber() {
    when(bedNumberConverter.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bedConverter.toResponseWithoutNumber(bed, stars)).thenReturn(bedResponse);
  }

  @Test
  public void add_withBedRequest_shouldAddBed() {
    bedService.add(bedRequest);

    verify(bedRepository).add(eq(bed));
  }

  @Test
  public void add_withBedRequest_shouldReturnBedNumber() {
    String actualBedNumber = bedService.add(bedRequest);

    assertEquals(bedNumber.toString(), actualBedNumber);
  }

  @Test
  public void add_shouldGetLocation() {
    bedService.add(bedRequest);

    verify(locationService).getLocation(eq(bedRequest.getZipCode()));
  }

  @Test
  public void getAll_shouldGetBedsWithQuery() {
    List<BedResponse> bedResponses = bedService.getAll(params);

    assertTrue(bedResponses.contains(bedResponse));
    assertTrue(bedResponses.contains(otherBedResponse));
  }

  @Test
  public void getAll_shouldOrderInDecreasingOrderOfStars() {
    List<BedResponse> bedResponses = bedService.getAll(params);

    assertSame(bedResponse, bedResponses.get(0));
    assertSame(otherBedResponse, bedResponses.get(1));
  }

  @Test
  public void getByNumber_withNumber_shouldGetBed() {
    BedResponse actualBedResponse = bedService.getByNumber(bedNumber.toString());

    assertSame(bedResponse, actualBedResponse);
  }
}
