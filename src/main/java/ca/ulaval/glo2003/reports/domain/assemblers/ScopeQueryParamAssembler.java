package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import com.google.inject.Inject;
import java.time.Year;
import java.util.List;
import java.util.Map;

// TODO : Test ScopeQueryParamAssembler
public class ScopeQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String SCOPE_PARAM = "scope";
  private final ReportScopeBuilder reportScopeBuilder;

  // TODO : Inject ConfigurationService and get default year
  @Inject
  public ScopeQueryParamAssembler(ReportScopeBuilder reportScopeBuilder) {
    this.reportScopeBuilder = reportScopeBuilder;
  }

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> scope = params.get(SCOPE_PARAM);
    ReportScope reportScope;
    TimePeriod timePeriod = new TimeYear(Year.of(2020)).toPeriod();

    if (scope != null) {
      ReportScopes reportScopeType = ReportScopes.get(scope.get(0));
      reportScope =
          reportScopeBuilder
              .aReportScope()
              .withType(reportScopeType)
              .withBookingPeriod(timePeriod)
              .build();
    } else {
      reportScope = reportScopeBuilder.aReportScope().withBookingPeriod(timePeriod).build();
    }

    return builder.withPeriods(reportScope.getReportPeriods());
  }
}
