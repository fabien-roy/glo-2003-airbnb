package ca.ulaval.glo2003.beds.domain.queries;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryListTest {

  private BedQueryList subQuery = mock(BedQueryList.class);
  private BedQueryList otherSubQuery = mock(BedQueryList.class);
  private List<Bed> beds;

  @BeforeEach
  public void setUpMocks() {
    Bed bed = mock(Bed.class);
    beds = Collections.singletonList(bed);
    subQuery = mock(BedQueryList.class);
  }

  @Test
  public void filter_withQuery_shouldFilterWithQuery() {
    BedQueryList query = new BedQueryList(Collections.singletonList(subQuery));

    query.filter(beds);

    verify(subQuery).filter(eq(beds));
  }

  @Test
  public void filter_withMultipleQueries_shouldFilterWithQueries() {
    BedQueryList query = new BedQueryList(Arrays.asList(subQuery, otherSubQuery));

    query.filter(beds);

    verify(subQuery).filter(eq(beds));
    verify(otherSubQuery).filter(eq(beds));
  }
}
