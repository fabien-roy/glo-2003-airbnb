package ca.ulaval.glo2003.beds.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createZipCode;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.BedResponseBuilder.aBedResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private static BedService bedService;
  private static BedFactory bedFactory;
  private static BedMapper bedMapper;
  private static BedNumberMapper bedNumberMapper;
  private static BedMatcherMapper bedMatcherMapper;
  private static BedRepository bedRepository;
  private static BedStarsCalculator bedStarsCalculator;
  private static ZippopotamusClient zippopotamusClient;

  private UUID bedNumber = createBedNumber();
  private ZipCode origin = createZipCode();
  private ZipCode validatedZipCode = createZipCode();
  private Bed bed = aBed().withBedNumber(bedNumber).build();
  private Bed otherBed = aBed().build();
  private BedMatcher bedMatcher = mock(BedMatcher.class);
  private BedRequest bedRequest = aBedRequest().build();
  private int stars = 4;
  private int otherStars = 2;
  private BedResponse bedResponse = aBedResponse().withStars(stars).build();
  private BedResponse otherBedResponse = aBedResponse().withStars(otherStars).build();
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpService() {
    bedFactory = mock(BedFactory.class);
    bedMapper = mock(BedMapper.class);
    bedNumberMapper = mock(BedNumberMapper.class);
    bedMatcherMapper = mock(BedMatcherMapper.class);
    bedRepository = mock(BedRepository.class);
    bedStarsCalculator = mock(BedStarsCalculator.class);
    zippopotamusClient = mock(ZippopotamusClient.class);
    bedService =
        new BedService(
            bedFactory,
            bedMapper,
            bedNumberMapper,
            bedMatcherMapper,
            bedRepository,
            bedStarsCalculator,
            zippopotamusClient);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedMatcher.matches(bed)).thenReturn(true);
    when(bedMatcher.matches(otherBed)).thenReturn(true);
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedMapper.fromRequest(bedRequest)).thenReturn(bed);
    when(zippopotamusClient.validateZipCode(bedRequest.getZipCode())).thenReturn(validatedZipCode);
    when(bedFactory.create(bed, validatedZipCode)).thenReturn(bed);
  }

  @BeforeEach
  public void setUpMocksForGetAll() {
    when(bedRepository.getAll()).thenReturn(Arrays.asList(bed, otherBed));
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
  public void getAll_withParams_shouldGetMatchingBedsWithCorrectAttributes() {
    when(bedMatcher.matches(otherBed)).thenReturn(false);

    List<BedResponse> bedResponses = bedService.getAll(params);

    assertTrue(bedResponses.contains(bedResponse));
    assertFalse(bedResponses.contains(otherBedResponse));
  }

  @Test
  public void getAll_withParams_shouldGetMatchingBedsInDecreasingOrderOfStars() {
    List<BedResponse> bedResponses = bedService.getAll(params);

    assertSame(bedResponse, bedResponses.get(0));
    assertSame(otherBedResponse, bedResponses.get(1));
  }

  @Test
  public void getAll_withoutOrigin_shouldNotMatchWithZipCode() {
    bedService.getAll(params);

    verify(bedMatcher, never()).setOrigin(any());
  }

  @Test
  public void getAll_withOrigin_shouldMatchWithValidateZipCode() {
    when(bedMatcher.getOrigin()).thenReturn(origin);

    bedService.getAll(params);

    verify(bedMatcher).setOrigin(eq(validatedZipCode));
  }

  @Test
  public void getByNumber_withNumber_shouldGetBed() {
    BedResponse actualBedResponse = bedService.getByNumber(bedNumber.toString());

    assertSame(bedResponse, actualBedResponse);
  }
}
