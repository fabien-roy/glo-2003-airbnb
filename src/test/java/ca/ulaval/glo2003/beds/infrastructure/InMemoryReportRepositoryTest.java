package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedNumber;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryReportRepositoryTest {

  private BedRepository bedRepository;

  private List<Bed> filteredBeds;
  private Bed bed;
  private Bed otherBed;
  private Bed nonExistentBed;
  private BedNumber bedNumber;
  private BedNumber nonExistentBedNumber;
  private InMemoryBedQuery bedQuery;

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
    bedQuery = mock(InMemoryBedQuery.class);
    when(bedQuery.execute()).thenReturn(filteredBeds);
  }

  @Test
  public void add_shouldAddBed() {
    bedRepository.add(bed);
    Bed actualBed = bedRepository.getByNumber(bedNumber);

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
  public void getAll_withQuery_shouldReturnFilteredBeds() {
    bedRepository.add(bed);

    List<Bed> actualBeds = bedRepository.getAll(bedQuery);

    assertSame(filteredBeds, actualBeds);
  }

  @Test
  public void getAll_withQuery_shouldUseQuery() {
    bedRepository.add(bed);

    bedRepository.getAll(bedQuery);

    verify(bedQuery).setBeds(eq(Collections.singletonList(bed)));
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
