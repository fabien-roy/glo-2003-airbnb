package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import com.google.inject.Inject;
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
    ReservationPeriod reservationPeriod = ReservationPeriod.ofYear(2020);

    if (scope != null) {
      ReportScopes reportScopeType = ReportScopes.get(scope.get(0));
      reportScope =
          reportScopeBuilder
              .aReportScope()
              .withType(reportScopeType)
              .withBookingPeriod(reservationPeriod)
              .build();
    } else {
      reportScope = reportScopeBuilder.aReportScope().withBookingPeriod(reservationPeriod).build();
    }

    return builder.withPeriods(reportScope.getReportPeriods());
  }
}
