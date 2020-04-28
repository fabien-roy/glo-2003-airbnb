package ca.ulaval.glo2003.reports.domain;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.assemblers.ReportQueryParamAssembler;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportQueryFactoryTest {

  private static ReportQueryFactory reportQueryFactory;
  private static ReportQueryBuilder reportQueryBuilder = mock(ReportQueryBuilder.class);
  private static ReportQueryBuilder filteredReportQueryBuilder = mock(ReportQueryBuilder.class);

  private ReportQueryParamAssembler firstQueryAssembler = mock(ReportQueryParamAssembler.class);
  private ReportQuery query = mock(ReportQuery.class);
  private ReportQuery filteredQuery = mock(ReportQuery.class);
  private Map<String, List<String>> params = new HashMap<>();

  private Set<ReportQueryParamAssembler> queryParamAssemblers =
      new HashSet<>(Collections.singletonList(firstQueryAssembler));

  @BeforeEach
  public void setUpMocks() {
    when(reportQueryBuilder.aReportQuery()).thenReturn(reportQueryBuilder);
    when(reportQueryBuilder.build()).thenReturn(query);
    when(filteredReportQueryBuilder.build()).thenReturn(filteredQuery);
    when(firstQueryAssembler.assemble(reportQueryBuilder, params))
        .thenReturn(filteredReportQueryBuilder);
  }

  @Test
  public void create_withoutAssembler_shouldCreateQuery() {
    reportQueryFactory = new ReportQueryFactory(reportQueryBuilder, Collections.emptySet());

    ReportQuery actualQuery = reportQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withAssemblers_shouldCreateQuery() {
    reportQueryFactory = new ReportQueryFactory(reportQueryBuilder, queryParamAssemblers);

    ReportQuery actualQuery = reportQueryFactory.create(params);

    assertSame(filteredQuery, actualQuery);
  }
}
