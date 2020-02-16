package ca.ulaval.glo2003.beds.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import java.util.List;
import java.util.UUID;
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
    UUID bedNumber = mock(UUID.class);

    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(bedNumber));
  }

  @Test
  private void getByNumber_withNonExistentNumber_shouldThrowBedNotFoundException() {
    UUID existentBedNumber = mock(UUID.class);
    UUID nonExistentBedNumber = mock(UUID.class);
    Bed existentBed = mock(Bed.class);
    when(existentBed.getNumber()).thenReturn(existentBedNumber);
    bedRepository.add(existentBed);

    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(nonExistentBedNumber));
  }

  @Test
  private void getByNumber_withOneBed_shouldGetBed() {
    UUID bedNumber = mock(UUID.class);
    Bed expectedBed = mock(Bed.class);
    when(expectedBed.getNumber()).thenReturn(bedNumber);
    bedRepository.add(expectedBed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(expectedBed, actualBed);
  }

  @Test
  private void getByNumber_withMultipleBeds_shouldGetBed() {
    UUID bedNumber = mock(UUID.class);
    UUID otherBedNumber = mock(UUID.class);
    Bed expectedBed = mock(Bed.class);
    when(expectedBed.getNumber()).thenReturn(bedNumber);
    Bed otherBed = mock(Bed.class);
    when(otherBed.getNumber()).thenReturn(otherBedNumber);
    bedRepository.add(expectedBed);
    bedRepository.add(otherBed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(expectedBed, actualBed);
  }
}
