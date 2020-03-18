package ca.ulaval.glo2003.beds.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
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
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
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

  private UUID bedNumber = createBedNumber();
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
    bedService =
        new BedService(
            bedFactory,
            bedMapper,
            bedNumberMapper,
            bedMatcherMapper,
            bedRepository,
            bedStarsCalculator);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedMatcher.matches(bed)).thenReturn(true);
    when(bedMatcher.matches(otherBed)).thenReturn(true);
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedMapper.fromRequest(bedRequest)).thenReturn(bed);
    when(bedFactory.create(bed)).thenReturn(bed);
  }

  @BeforeEach
  public void setUpMocksForGetAll() {
    when(bedRepository.getAll()).thenReturn(Arrays.asList(bed, otherBed));
    when(bedStarsCalculator.calculateStars(bed)).thenReturn(stars);
    when(bedStarsCalculator.calculateStars(otherBed)).thenReturn(otherStars);
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
  public void add_withInvalidZipCode_shouldThrowInvalidZipCodeException() {
    BedRequest bedRequest = new BedRequest();
    bedRequest.setZipCode("146886486468");

    assertThrows(InvalidZipCodeException.class, () -> bedService.add(bedRequest));
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
  public void getAll_withParams_shouldThrowInvalidZipCodeExceptionIfOneZipCodeIsInvalid() {
    Map<String, String[]> params = new HashMap<>();
    BedMatcher bedMatcher = mock(BedMatcher.class);
    Bed expectedBed = mock(Bed.class);
    int expectedStars = 1;
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(expectedBed.getZipCode()).thenReturn("1234567");
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(expectedBed));
    when(bedMatcher.matches(expectedBed)).thenReturn(true);
    when(bedStarsCalculator.calculateStars(expectedBed)).thenReturn(expectedStars);
    when(bedMapper.toResponseWithNumber(expectedBed, expectedStars))
        .thenReturn(expectedBedResponse);

    assertThrows(InvalidZipCodeException.class, () -> bedService.getAll(params));
  }

  @Test
  public void getByNumber_withNumber_shouldGetBed() {
    BedResponse actualBedResponse = bedService.getByNumber(bedNumber.toString());

    assertSame(bedResponse, actualBedResponse);
  }
}
