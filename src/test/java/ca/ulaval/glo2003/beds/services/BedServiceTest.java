package ca.ulaval.glo2003.beds.services;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.domain.BedRepository;
import org.junit.jupiter.api.BeforeEach;

class BedServiceTest {

  private BedService bedService;
  private BedRepository bedRepository;

  @BeforeEach
  private void setUpService() {
    bedRepository = mock(BedRepository.class);
    bedService = new BedService(bedRepository);
  }

  // TODO
}
