package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetricsQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String METRICS_PARAM = "metrics";

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> metrics = params.get(METRICS_PARAM);

    return metrics != null ? builder.withMetrics(parseMetrics(metrics)) : builder;
  }

  private List<ReportMetrics> parseMetrics(List<String> metrics) {
    return metrics.stream().map(ReportMetrics::get).collect(Collectors.toList());
  }
}
