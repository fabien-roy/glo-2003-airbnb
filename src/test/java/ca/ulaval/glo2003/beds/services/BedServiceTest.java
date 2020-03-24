package ca.ulaval.glo2003.beds.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createZipCode;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.BedResponseBuilder.aBedResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQuery;
import ca.ulaval.glo2003.beds.mappers.BedMapper;
import ca.ulaval.glo2003.beds.mappers.BedNumberMapper;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private static BedService bedService;
  private static BedFactory bedFactory = mock(BedFactory.class);
  private static BedQueryFactory bedQueryFactory = mock(BedQueryFactory.class);
  private static BedMapper bedMapper = mock(BedMapper.class);
  private static BedNumberMapper bedNumberMapper = mock(BedNumberMapper.class);
  private static BedRepository bedRepository = mock(BedRepository.class);
  private static BedStarsCalculator bedStarsCalculator = mock(BedStarsCalculator.class);
  private static ZippopotamusClient zippopotamusClient = mock(ZippopotamusClient.class);

  private UUID bedNumber = createBedNumber();
  private ZipCode origin = createZipCode();
  private ZipCode validatedZipCode = createZipCode();
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
            bedMapper,
            bedNumberMapper,
            bedRepository,
            bedStarsCalculator,
            zippopotamusClient);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedMapper.fromRequest(bedRequest)).thenReturn(bed);
    when(zippopotamusClient.validateZipCode(bedRequest.getZipCode())).thenReturn(validatedZipCode);
    when(bedFactory.create(bed, validatedZipCode)).thenReturn(bed);
  }

  @BeforeEach
  public void setUpMocksForGetAll() {
    InMemoryBedQuery bedQuery = mock(InMemoryBedQuery.class);
    when(bedQueryFactory.create(params)).thenReturn(bedQuery);
    when(bedRepository.getAll(bedQuery)).thenReturn(Arrays.asList(bed, otherBed));
    when(bedStarsCalculator.calculateStars(bed)).thenReturn(stars);
    when(bedStarsCalculator.calculateStars(otherBed)).thenReturn(otherStars);
    when(zippopotamusClient.validateZipCode(origin.getValue())).thenReturn(validatedZipCode);
    when(bedMapper.toResponseWithNumber(bed, stars)).thenReturn(bedResponse);
    when(bedMapper.toResponseWithNumber(otherBed, otherStars)).thenReturn(otherBedResponse);
  }

  @BeforeEach
  public void setUpMocksForGetByNumber() {
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bedMapper.toResponseWithoutNumber(bed, stars)).thenReturn(bedResponse);
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
  public void add_shouldValidateZipCode() {
    bedService.add(bedRequest);

    verify(zippopotamusClient).validateZipCode(eq(bedRequest.getZipCode()));
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
  public void getResponse_shouldGetBedResponse() {
    BedResponse actualBedResponse = bedService.getResponse(bedNumber.toString());

    assertSame(bedResponse, actualBedResponse);
  }

  @Test
  public void get_shouldGetBed() {
    Bed actualBed = bedService.get(bedNumber.toString());

    assertSame(bed, actualBed);
  }

  @Test
  public void update_shouldUpdateBed() {
    bedService.update(bed);

    verify(bedRepository).update(bed);
  }
}
