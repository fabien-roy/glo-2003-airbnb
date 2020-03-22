package ca.ulaval.glo2003.beds.infrastructure.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryPackageFilterTest {

  private static InMemoryBedFilter filter;
  private static Packages packageName = Packages.BLOODTHIRSTY;

  private Bed bedWithPackage;
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryPackageFilter(packageName);
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithPackage = mock(Bed.class);
    Bed otherBed = mock(Bed.class);
    when(bedWithPackage.isPackageAvailable(packageName)).thenReturn(true);
    when(otherBed.isPackageAvailable(packageName)).thenReturn(false);
    beds = Arrays.asList(bedWithPackage, otherBed);
  }

  @Test
  public void filter_shouldFilterBedType() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithPackage, filteredBeds.get(0));
  }
}
