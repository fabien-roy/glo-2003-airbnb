package ca.ulaval.glo2003.beds.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedRepositoryTest {

  private BedRepository bedRepository;

  @BeforeEach
  private void setUpRepository() {
    bedRepository = new InMemoryBedRepository();
  }

  @Test
  private void add_shouldAddBed() {
    Bed expectedBed = mock(Bed.class);

    bedRepository.add(expectedBed);
    Bed actualBed = bedRepository.getAll().get(0);

    assertSame(expectedBed, actualBed);
  }

  @Test
  private void getAll_withOneBed_shouldGetOneBed() {
    Bed expectedBed = mock(Bed.class);
    bedRepository.add(expectedBed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(1, actualBeds.size());
    assertSame(expectedBed, actualBeds.get(0));
  }

  @Test
  private void getAll_withMultipleBeds_shouldGetMultipleBeds() {
    Bed expectedBed = mock(Bed.class);
    Bed otherExpectedBed = mock(Bed.class);
    bedRepository.add(expectedBed);
    bedRepository.add(otherExpectedBed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(2, actualBeds.size());
    assertTrue(actualBeds.contains(expectedBed));
    assertTrue(actualBeds.contains(otherExpectedBed));
  }

  @Test
  private void getByNumber_withNoBed_shouldThrowBedNotFoundException() {
    // TODO
  }

  @Test
  private void getByNumber_withNonExistentNumber_shouldThrowBedNotFoundException() {
    // TODO
  }

  @Test
  private void getByNumber_withOneBed_shouldGetBed() {
    // TODO
  }

  @Test
  private void getByNumber_withMultipleBeds_shouldGetBed() {
    // TODO
  }
}
