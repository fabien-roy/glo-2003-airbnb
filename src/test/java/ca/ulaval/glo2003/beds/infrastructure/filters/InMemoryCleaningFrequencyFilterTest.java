package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryCleaningFrequencyFilterTest {

  private static InMemoryBedFilter filter;
  private static CleaningFrequencies cleaningFrequency = CleaningFrequencies.WEEKLY;
  private static CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;

  private Bed bedWithCleaningFrequency;
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryCleaningFrequencyFilter(cleaningFrequency);
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithCleaningFrequency = aBed().withCleaningFrequency(cleaningFrequency).build();
    Bed otherBed = aBed().withCleaningFrequency(otherCleaningFrequency).build();
    beds = Arrays.asList(bedWithCleaningFrequency, otherBed);
  }

  @Test
  public void filter_shouldFilterCleaningFrequency() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithCleaningFrequency, filteredBeds.get(0));
  }
}
