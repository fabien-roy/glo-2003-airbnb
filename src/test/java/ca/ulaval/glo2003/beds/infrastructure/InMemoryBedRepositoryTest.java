package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedQuery;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryBedRepositoryTest {

  private BedRepository bedRepository;

  private List<Bed> filteredBeds;
  private Bed bed;
  private Bed otherBed;
  private Bed nonExistentBed;
  private UUID bedNumber;
  private UUID nonExistentBedNumber;
  private BedQuery bedQuery;

  @BeforeEach
  public void setUpRepository() {
    bedRepository = new InMemoryBedRepository();
  }

  @BeforeEach
  public void setUpBeds() {
    bedNumber = createBedNumber();
    nonExistentBedNumber = createBedNumber();
    bed = aBed().withBedNumber(bedNumber).build();
    otherBed = aBed().build();
    nonExistentBed = aBed().withBedNumber(nonExistentBedNumber).build();
  }

  @BeforeEach
  public void setUpQuery() {
    filteredBeds = Collections.singletonList(aBed().build());
    bedQuery = mock(BedQuery.class);
    when(bedQuery.filter(any())).thenReturn(filteredBeds);
  }

  @Test
  public void add_shouldAddBed() {
    bedRepository.add(bed);
    Bed actualBed = bedRepository.getAll().get(0);

    assertSame(bed, actualBed);
  }

  @Test
  public void update_shouldUpdateBed() {
    bedRepository.add(bed);
    Bed updatedBed = aBed().withBedNumber(bedNumber).build();

    bedRepository.update(updatedBed);
    Bed newBed = bedRepository.getByNumber(bedNumber);

    assertSame(updatedBed, newBed);
  }

  @Test
  public void update_withNonExistentBedNumber_shouldThrowBedNotFoundException() {
    bedRepository.add(bed);

    assertThrows(BedNotFoundException.class, () -> bedRepository.update(nonExistentBed));
  }

  @Test
  public void getAll_withOneBed_shouldGetOneBed() {
    bedRepository.add(bed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(1, actualBeds.size());
    assertSame(bed, actualBeds.get(0));
  }

  @Test
  public void getAll_withMultipleBeds_shouldGetMultipleBeds() {
    bedRepository.add(bed);
    bedRepository.add(otherBed);

    List<Bed> actualBeds = bedRepository.getAll();

    assertEquals(2, actualBeds.size());
    assertTrue(actualBeds.contains(bed));
    assertTrue(actualBeds.contains(otherBed));
  }

  @Test
  public void getAll_withQuery_shouldUseQuery() {
    bedRepository.add(bed);

    List<Bed> actualBeds = bedRepository.getAll(bedQuery);

    assertSame(filteredBeds, actualBeds);
  }

  @Test
  public void getByNumber_withNoBed_shouldThrowBedNotFoundException() {
    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(bedNumber));
  }

  @Test
  public void getByNumber_withNonExistentNumber_shouldThrowBedNotFoundException() {
    bedRepository.add(bed);

    assertThrows(BedNotFoundException.class, () -> bedRepository.getByNumber(nonExistentBedNumber));
  }

  @Test
  public void getByNumber_withOneBed_shouldGetBed() {
    bedRepository.add(bed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(bed, actualBed);
  }

  @Test
  public void getByNumber_withMultipleBeds_shouldGetBed() {
    bedRepository.add(bed);
    bedRepository.add(otherBed);

    Bed actualBed = bedRepository.getByNumber(bedNumber);

    assertSame(bed, actualBed);
  }
}
