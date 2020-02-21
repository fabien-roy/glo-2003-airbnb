package ca.ulaval.glo2003.beds.services;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BedServiceTest {

  private BedService bedService;
  private BedMapper bedMapper;
  private BedRepository bedRepository;

  @BeforeEach
  public void setUpService() {
    bedMapper = mock(BedMapper.class);
    bedRepository = mock(BedRepository.class);
    bedService = new BedService(bedMapper, bedRepository);
  }

  @Test
  public void getAll_withParams_shouldMatchBedWithCorrectAttributes() {
    // TODO : This test should test that the requestBed returned by mapper is correctly sent to
    // matcher

  }
}
