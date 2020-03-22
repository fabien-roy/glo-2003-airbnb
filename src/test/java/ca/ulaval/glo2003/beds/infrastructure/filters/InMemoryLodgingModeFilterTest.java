package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryLodgingModeFilterTest {

  private static BedFilter filter;
  private static LodgingModes lodgingMode = LodgingModes.PRIVATE;
  private static LodgingModes otherLodgingMode = LodgingModes.COHABITATION;

  private Bed bedWithLodgingMode;
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryLodgingModeFilter(lodgingMode);
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithLodgingMode = aBed().withLodgingMode(lodgingMode).build();
    Bed otherBed = aBed().withLodgingMode(otherLodgingMode).build();
    beds = Arrays.asList(bedWithLodgingMode, otherBed);
  }

  @Test
  public void filter_shouldFilterLodgingMode() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithLodgingMode, filteredBeds.get(0));
  }
}
