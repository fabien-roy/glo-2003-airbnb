package ca.ulaval.glo2003.beds.domain.queries;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.BedQuery;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryTest {

  private BedFilter filter = mock(BedFilter.class);
  private BedFilter otherFilter = mock(BedFilter.class);
  private List<Bed> beds;
  private List<Bed> filteredBeds;
  private List<Bed> otherFilteredBeds;

  @BeforeEach
  public void setUpMocks() {
    Bed bed = aBed().build();
    Bed filteredBed = aBed().build();
    Bed otherFilteredBed = aBed().build();
    beds = Arrays.asList(bed, filteredBed, otherFilteredBed);
    filteredBeds = Arrays.asList(filteredBed, otherFilteredBed);
    otherFilteredBeds = Collections.singletonList(otherFilteredBed);
    filter = mock(BedFilter.class);
    otherFilter = mock(BedFilter.class);
    when(filter.filter(beds)).thenReturn(filteredBeds);
    when(otherFilter.filter(filteredBeds)).thenReturn(otherFilteredBeds);
  }

  @Test
  public void filter_withQuery_shouldFilterWithQuery() {
    BedQuery query = new BedQuery(Collections.singletonList(filter));

    List<Bed> actualBeds = query.filter(beds);

    assertEquals(filteredBeds.size(), actualBeds.size());
    assertTrue(filteredBeds.containsAll(actualBeds));
  }

  @Test
  public void filter_withMultipleQueries_shouldFilterWithQueries() {
    BedQuery query = new BedQuery(Arrays.asList(filter, otherFilter));

    List<Bed> actualBeds = query.filter(beds);

    assertEquals(otherFilteredBeds.size(), actualBeds.size());
    assertTrue(otherFilteredBeds.containsAll(actualBeds));
  }
}
