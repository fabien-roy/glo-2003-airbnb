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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryTest {

  private static InMemoryBedQuery query;
  private static InMemoryBedFilter filter = mock(InMemoryBedFilter.class);
  private List<Bed> beds;
  private List<Bed> filteredBeds;

  @BeforeAll
  public static void setUpQuery() {
    query = new InMemoryBedQuery(Collections.singletonList(filter));
  }

  @BeforeEach
  public void setUpMocks() {
    Bed bed = aBed().build();
    Bed filteredBed = aBed().build();
    beds = Arrays.asList(bed, filteredBed);
    filteredBeds = Collections.singletonList(filteredBed);
    when(filter.filter(beds)).thenReturn(filteredBeds);
  }

  @Test
  public void filter_withQuery_shouldFilterWithQuery() {
    query.setBeds(beds);

    List<Bed> actualBeds = query.execute();

    assertEquals(filteredBeds.size(), actualBeds.size());
    assertTrue(filteredBeds.containsAll(actualBeds));
  }
}
