package ca.ulaval.glo2003.beds.services;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private BedService bedService;
  private BedFactory bedFactory;
  private BedMapper bedMapper;
  private BedNumberMapper bedNumberMapper;
  private BedMatcherMapper bedMatcherMapper;
  private BedRepository bedRepository;
  private BedStarsCalculator bedStarsCalculator;

  @BeforeEach
  public void setUpService() {
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

  @Test
  public void add_withBedRequest_shouldAddBed() {
    BedRequest bedRequest = mock(BedRequest.class);
    Bed expectedBed = mock(Bed.class);
    when(bedMapper.fromRequest(bedRequest)).thenReturn(expectedBed);
    when(bedFactory.create(expectedBed)).thenReturn(expectedBed);
    when(expectedBed.getNumber()).thenReturn(mock(UUID.class));

    bedService.add(bedRequest);

    verify(bedRepository).add(eq(expectedBed));
  }

  @Test
  public void add_withBedRequest_shouldReturnBedNumber() {
    UUID expectedBedNumber = mock(UUID.class);
    BedRequest bedRequest = mock(BedRequest.class);
    Bed bed = mock(Bed.class);
    when(bedMapper.fromRequest(bedRequest)).thenReturn(bed);
    when(bedFactory.create(bed)).thenReturn(bed);
    when(bed.getNumber()).thenReturn(expectedBedNumber);

    String bedNumber = bedService.add(bedRequest);

    assertEquals(expectedBedNumber.toString(), bedNumber);
  }

  @Test
  public void add_withInvalidZipCode_shouldThrowInvalidZipCodeException() {
    BedRequest bedRequest = new BedRequest();
    bedRequest.setZipCode("146886486468");

    assertThrows(InvalidZipCodeException.class, () -> bedService.add(bedRequest));
  }

  @Test
  public void getAll_withParams_shouldGetMatchingBedsWithCorrectAttributes() {
    Map<String, String[]> params = new HashMap<>();
    BedMatcher bedMatcher = mock(BedMatcher.class);
    Bed expectedBed = mock(Bed.class);
    int expectedStars = 1;
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(expectedBed));
    when(bedMatcher.matches(expectedBed)).thenReturn(true);
    when(bedStarsCalculator.calculateStars(expectedBed)).thenReturn(expectedStars);
    when(bedMapper.toResponseWithNumber(expectedBed, expectedStars))
        .thenReturn(expectedBedResponse);

    List<BedResponse> bedResponses = bedService.getAll(params);

    assertEquals(1, bedResponses.size());
    assertSame(expectedBedResponse, bedResponses.get(0));
  }

  @Test
  public void getAll_withParams_shouldGetMatchingBedsInDecreasingOrderOfStars() {
    Map<String, String[]> params = new HashMap<>();
    BedMatcher bedMatcher = mock(BedMatcher.class);
    int firstExpectedStars = 5;
    int secondExpectedStars = 3;
    Bed firstExpectedBed = mock(Bed.class);
    Bed secondExpectedBed = mock(Bed.class);
    BedResponse firstExpectedBedResponse = mock(BedResponse.class);
    BedResponse secondExpectedBedResponse = mock(BedResponse.class);
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedRepository.getAll()).thenReturn(Arrays.asList(secondExpectedBed, firstExpectedBed));
    when(bedMatcher.matches(any(Bed.class))).thenReturn(true);
    when(bedStarsCalculator.calculateStars(firstExpectedBed)).thenReturn(firstExpectedStars);
    when(bedStarsCalculator.calculateStars(secondExpectedBed)).thenReturn(secondExpectedStars);
    when(bedMapper.toResponseWithNumber(firstExpectedBed, firstExpectedStars))
        .thenReturn(firstExpectedBedResponse);
    when(bedMapper.toResponseWithNumber(secondExpectedBed, secondExpectedStars))
        .thenReturn(secondExpectedBedResponse);
    when(firstExpectedBedResponse.getStars()).thenReturn(firstExpectedStars);
    when(secondExpectedBedResponse.getStars()).thenReturn(secondExpectedStars);

    List<BedResponse> bedResponses = bedService.getAll(params);

    assertEquals(2, bedResponses.size());
    assertSame(firstExpectedBedResponse, bedResponses.get(0));
    assertSame(secondExpectedBedResponse, bedResponses.get(1));
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
    String requestedNumber = "requestedNumber";
    UUID bedNumber = mock(UUID.class);
    Bed expectedBed = mock(Bed.class);
    int expectedStars = 1;
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedNumberMapper.fromString(requestedNumber)).thenReturn(bedNumber);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(expectedBed);
    when(bedStarsCalculator.calculateStars(expectedBed)).thenReturn(expectedStars);
    when(bedMapper.toResponseWithoutNumber(expectedBed, expectedStars))
        .thenReturn(expectedBedResponse);

    BedResponse bedResponse = bedService.getByNumber(requestedNumber);

    assertEquals(expectedBedResponse, bedResponse);
  }
}
