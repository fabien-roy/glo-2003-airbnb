package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedTypeFilterTest {

  private static BedFilter filter;
  private static BedTypes bedType = BedTypes.LATEX;
  private static BedTypes otherBedType = BedTypes.SPRINGS;

  private Bed bedWithBedType;
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryBedTypeFilter(bedType);
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithBedType = aBed().withBedType(bedType).build();
    Bed otherBed = aBed().withBedType(otherBedType).build();
    beds = Arrays.asList(bedWithBedType, otherBed);
  }

  @Test
  public void filter_shouldFilterBedType() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithBedType, filteredBeds.get(0));
  }
}
