package ca.ulaval.glo2003.beds.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedMatcher;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
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

  @BeforeEach
  public void setUpService() {
    bedFactory = mock(BedFactory.class);
    bedMapper = mock(BedMapper.class);
    bedNumberMapper = mock(BedNumberMapper.class);
    bedMatcherMapper = mock(BedMatcherMapper.class);
    bedRepository = mock(BedRepository.class);
    bedService =
        new BedService(bedFactory, bedMapper, bedNumberMapper, bedMatcherMapper, bedRepository);
  }

  @Test
  public void getAll_withParams_shouldGetMatchingBedsWithCorrectAttributes() {
    Map<String, String> params = new HashMap<>();
    BedMatcher bedMatcher = mock(BedMatcher.class);
    Bed expectedBed = mock(Bed.class);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedMatcherMapper.fromRequestParams(params)).thenReturn(bedMatcher);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(expectedBed));
    when(bedMatcher.matches(expectedBed)).thenReturn(true);
    when(bedMapper.toResponse(expectedBed)).thenReturn(expectedBedResponse);

    List<BedResponse> bedResponses = bedService.getAll(params);

    assertEquals(1, bedResponses.size());
    assertSame(expectedBedResponse, bedResponses.get(0));
  }

  @Test
  public void getByNumber_withNumber_shouldGetBed() {
    String requestedNumber = "requestedNumber";
    UUID bedNumber = mock(UUID.class);
    Bed expectedBed = mock(Bed.class);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedNumberMapper.fromString(requestedNumber)).thenReturn(bedNumber);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(expectedBed);
    when(bedMapper.toResponse(expectedBed)).thenReturn(expectedBedResponse);

    BedResponse bedResponse = bedService.getByNumber(requestedNumber);

    assertEquals(expectedBedResponse, bedResponse);
  }
}
