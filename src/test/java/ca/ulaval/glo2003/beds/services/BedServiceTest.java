package ca.ulaval.glo2003.beds.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private BedService bedService;
  private BedMapper bedMapper;
  private BedNumberMapper bedNumberMapper;
  private BedRepository bedRepository;

  @BeforeEach
  public void setUpService() {
    bedMapper = mock(BedMapper.class);
    bedNumberMapper = mock(BedNumberMapper.class);
    bedRepository = mock(BedRepository.class);
    bedService = new BedService(bedMapper, bedNumberMapper, bedRepository);
  }

  @Test
  public void getAll_withParams_shouldMatchBedWithCorrectAttributes() {
    Map<String, String> params = new HashMap<>();
    Bed matchingBed = mock(Bed.class);
    Bed expectedBed = mock(Bed.class);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedMapper.fromRequestParams(params)).thenReturn(matchingBed);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(expectedBed));
    when(expectedBed.matches(matchingBed)).thenReturn(true);
    when(bedMapper.toResponse(expectedBed)).thenReturn(expectedBedResponse);

    List<BedResponse> bedResponses = bedService.getAll(params);

    assertEquals(1, bedResponses.size());
    assertSame(expectedBedResponse, bedResponses.get(0));
  }
}
