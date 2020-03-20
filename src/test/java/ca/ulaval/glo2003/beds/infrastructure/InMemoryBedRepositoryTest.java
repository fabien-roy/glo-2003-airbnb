package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryBedRepositoryTest {

  private BedRepository bedRepository;

  @BeforeEach
  public void setUpRepository() {
    bedRepository = new InMemoryBedRepository();
  }

  @Test
  public void add_shouldAddBed() {
    Bed expectedBed = aBed().build();

    bedRepository.add(expectedBed);
    Bed actualBed = bedRepository.getAll().get(0);

    assertSame(expectedBed, actualBed);
  }

  @Test
  public void update_shouldUpdateBed() {
    UUID bedNumber = createBedNumber();
    Bed originalBed = aBed().withBedNumber(bedNumber).build();
    Bed updatedBed = aBed().withBedNumber(bedNumber).build();
    bedRepository.add(originalBed);

    bedRepository.update(updatedBed);
    Bed newBed = bedRepository.getByNumber(bedNumber);

    assertSame(updatedBed, newBed);
  }

  @Test
  public void update_withNonExistentBedNumber_shouldThrowBedNotFoundException() {
    UUID bedNumber = createBedNumber();
    UUID nonExistentBedNumber = createBedNumber();
    Bed originalBed = aBed().withBedNumber(bedNumber).build();
    Bed updatedBed = aBed().withBedNumber(nonExistentBedNumber).build();
    bedRepository.add(originalBed);

    assertThrows(BedNotFoundException.class, () -> bedRepository.update(updatedBed));
  }

  @Test
  public void getAll_withOneBed_shouldGetOneBed() {
    Bed expectedBed = aBed().build();
    bedRepository.add(expectedBed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(1, actualBeds.size());
    assertSame(expectedBed, actualBeds.get(0));
  }

  @Test
  public void getAll_withMultipleBeds_shouldGetMultipleBeds() {
    Bed expectedBed = aBed().build();
    Bed otherExpectedBed = aBed().build();
    bedRepository.add(expectedBed);
    bedRepository.add(otherExpectedBed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(2, actualBeds.size());
    assertTrue(actualBeds.contains(expectedBed));
    assertTrue(actualBeds.contains(otherExpectedBed));
  }

  @Test
  public void getByNumber_withNoBed_shouldThrowBedNotFoundException() {
    UUID bedNumber = createBedNumber();

    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(bedNumber));
  }

  @Test
  public void getByNumber_withNonExistentNumber_shouldThrowBedNotFoundException() {
    UUID existentBedNumber = createBedNumber();
    UUID nonExistentBedNumber = createBedNumber();
    Bed existentBed = aBed().withBedNumber(existentBedNumber).build();
    bedRepository.add(existentBed);

    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(nonExistentBedNumber));
  }

  @Test
  public void getByNumber_withOneBed_shouldGetBed() {
    UUID bedNumber = createBedNumber();
    Bed expectedBed = aBed().withBedNumber(bedNumber).build();
    bedRepository.add(expectedBed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(expectedBed, actualBed);
  }

  @Test
  public void getByNumber_withMultipleBeds_shouldGetBed() {
    UUID bedNumber = createBedNumber();
    UUID otherBedNumber = createBedNumber();
    Bed expectedBed = aBed().withBedNumber(bedNumber).build();
    Bed otherBed = aBed().withBedNumber(otherBedNumber).build();
    bedRepository.add(expectedBed);
    bedRepository.add(otherBed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(expectedBed, actualBed);
  }
}
