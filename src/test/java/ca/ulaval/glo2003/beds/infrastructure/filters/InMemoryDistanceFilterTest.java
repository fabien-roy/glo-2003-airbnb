package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.locations.domain.Location;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryDistanceFilterTest {

  private static InMemoryBedFilter filter;
  private static Location origin = mock(Location.class);
  private static double maxDistance = 20d;

  private Bed bedWithinRadius;
  private static Location bedLocation = createLocation();
  private static Location otherBedLocation = createLocation();
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryDistanceFilter(origin, maxDistance);
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithinRadius = aBed().withLocation(bedLocation).build();
    Bed otherBed = aBed().withLocation(otherBedLocation).build();
    beds = Arrays.asList(bedWithinRadius, otherBed);
    when(origin.isWithinRadius(bedLocation, maxDistance)).thenReturn(true);
    when(origin.isWithinRadius(otherBedLocation, maxDistance)).thenReturn(false);
  }

  @Test
  public void filter_shouldFilterBedType() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithinRadius, filteredBeds.get(0));
  }
}
