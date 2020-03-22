package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryTest {

  private InMemoryBedFilter filter = mock(InMemoryBedFilter.class);
  private InMemoryBedFilter otherFilter = mock(InMemoryBedFilter.class);
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
    when(filter.filter(beds)).thenReturn(filteredBeds);
    when(otherFilter.filter(filteredBeds)).thenReturn(otherFilteredBeds);
  }

  @Test
  public void filter_withQuery_shouldFilterWithQuery() {
    InMemoryBedQuery query = new InMemoryBedQuery(Collections.singletonList(filter));
    query.setBeds(beds);

    List<Bed> actualBeds = query.execute();

    assertEquals(filteredBeds.size(), actualBeds.size());
    assertTrue(filteredBeds.containsAll(actualBeds));
  }

  @Test
  public void filter_withMultipleQueries_shouldFilterWithQueries() {
    InMemoryBedQuery query = new InMemoryBedQuery(Arrays.asList(filter, otherFilter));
    query.setBeds(beds);

    List<Bed> actualBeds = query.execute();

    assertEquals(otherFilteredBeds.size(), actualBeds.size());
    assertTrue(otherFilteredBeds.containsAll(actualBeds));
  }
}
