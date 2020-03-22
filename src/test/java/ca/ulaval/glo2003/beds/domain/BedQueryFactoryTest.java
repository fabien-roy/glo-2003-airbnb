package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;
import ca.ulaval.glo2003.beds.domain.assemblers.BedTypeQueryParamAssembler;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQuery;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryFactoryTest {

  private static BedQueryFactory bedQueryFactory;
  private static BedQueryBuilder bedQueryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder firstBedQueryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder secondBedQueryBuilder = mock(BedQueryBuilder.class);

  private BedTypeQueryParamAssembler queryAssembler = mock(BedTypeQueryParamAssembler.class);
  private BedTypeQueryParamAssembler otherQueryAssembler = mock(BedTypeQueryParamAssembler.class);
  private InMemoryBedQuery query = mock(InMemoryBedQuery.class);
  private InMemoryBedQuery filteredQuery = mock(InMemoryBedQuery.class);
  private InMemoryBedQuery otherFilteredQuery = mock(InMemoryBedQuery.class);
  private Map<String, String[]> params = new HashMap<>();

  private Set<BedQueryParamAssembler> queryParamAssemblers =
      new HashSet<>(Collections.singletonList(queryAssembler));
  private Set<BedQueryParamAssembler> otherQueryParamAssemblers =
      new HashSet<>(Arrays.asList(queryAssembler, otherQueryAssembler));

  @BeforeEach
  public void setUpMocks() {
    when(bedQueryBuilder.aBedQuery()).thenReturn(bedQueryBuilder);
    when(bedQueryBuilder.build()).thenReturn(query);
    when(firstBedQueryBuilder.build()).thenReturn(filteredQuery);
    when(secondBedQueryBuilder.build()).thenReturn(otherFilteredQuery);
    when(queryAssembler.assemble(bedQueryBuilder, params)).thenReturn(firstBedQueryBuilder);
    when(otherQueryAssembler.assemble(firstBedQueryBuilder, params))
        .thenReturn(secondBedQueryBuilder);
  }

  @Test
  public void create_withoutAssembler_shouldCreateQuery() {
    bedQueryFactory = new BedQueryFactory(bedQueryBuilder, Collections.emptySet());

    InMemoryBedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withSingleAssembler_shouldCreateQuery() {
    bedQueryFactory = new BedQueryFactory(bedQueryBuilder, queryParamAssemblers);

    InMemoryBedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(filteredQuery, actualQuery);
  }

  @Test
  public void create_withMultipleAssemblers_shouldCreateQuery() {
    bedQueryFactory = new BedQueryFactory(bedQueryBuilder, otherQueryParamAssemblers);

    InMemoryBedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(otherFilteredQuery, actualQuery);
  }
}
