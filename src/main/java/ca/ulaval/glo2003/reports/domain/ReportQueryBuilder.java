package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import java.util.List;

public interface ReportQueryBuilder<Q extends ReportQuery> {

  ReportQueryBuilder aReportQuery();

  ReportQueryBuilder withScope(ReportScopes scope);

  ReportQueryBuilder withMetrics(List<ReportMetrics> metrics);

  ReportQueryBuilder withDimensions(List<ReportDimensions> dimensions);

  Q build();
}
