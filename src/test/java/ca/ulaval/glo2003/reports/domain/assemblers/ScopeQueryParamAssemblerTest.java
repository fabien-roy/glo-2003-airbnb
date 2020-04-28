package ca.ulaval.glo2003.reports.domain.assemblers;

import static ca.ulaval.glo2003.reports.domain.assemblers.ScopeQueryParamAssembler.SCOPE_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import ca.ulaval.glo2003.reports.exceptions.InvalidReportScopeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScopeQueryParamAssemblerTest {

  private static ScopeQueryParamAssembler queryAssembler;
  private static ReportQueryBuilder queryBuilder = mock(ReportQueryBuilder.class);
  private static ReportQueryBuilder assembledQueryBuilder = mock(ReportQueryBuilder.class);

  private ReportScopes scope = ReportScopes.ANNUAL;
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new ScopeQueryParamAssembler();
  }

  @Test
  public void assemble_withoutCleaningFrequency_shouldReturnSameBuilder() {
    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(queryBuilder, actualQueryBuilder);
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withScope(scope)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withCleaningFrequency_shouldAssembleBuilder() {
    params.put(SCOPE_PARAM, Collections.singletonList(scope.toString()));

    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    params.put(SCOPE_PARAM, Collections.singletonList("invalidScope"));

    assertThrows(
        InvalidReportScopeException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
