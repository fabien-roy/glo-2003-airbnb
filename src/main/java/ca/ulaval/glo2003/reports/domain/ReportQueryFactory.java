package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.assemblers.ReportQueryParamAssembler;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class ReportQueryFactory {

  private final ReportQueryBuilder reportQueryBuilder;
  private final Set<ReportQueryParamAssembler> queryParamAssemblers;

  @Inject
  public ReportQueryFactory(
      ReportQueryBuilder reportQueryBuilder, Set<ReportQueryParamAssembler> queryParamAssemblers) {
    this.reportQueryBuilder = reportQueryBuilder;
    this.queryParamAssemblers = queryParamAssemblers;
  }

  public ReportQuery create(Map<String, List<String>> params) {
    ReportQueryBuilder builder = reportQueryBuilder.aReportQuery();

    for (ReportQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, params);

    return builder.build();
  }
}
