package ca.ulaval.glo2003.beds.domain.queries;

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

  @BeforeEach
  public void setUpMocks() {
    Bed bed = mock(Bed.class);
    beds = Collections.singletonList(bed);
    filter = mock(BedFilter.class);
  }

  @Test
  public void filter_withQuery_shouldFilterWithQuery() {
    BedQuery query = new BedQuery(Collections.singletonList(filter));

    query.filter(beds);

    verify(filter).filter(eq(beds));
  }

  @Test
  public void filter_withMultipleQueries_shouldFilterWithQueries() {
    BedQuery query = new BedQuery(Arrays.asList(filter, otherFilter));

    query.filter(beds);

    verify(filter).filter(eq(beds));
    verify(otherFilter).filter(eq(beds));
  }
}
