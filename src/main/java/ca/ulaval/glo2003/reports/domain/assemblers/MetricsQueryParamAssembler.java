package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO : Test MetricsQueryParamAssembler
public class MetricsQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String METRICS_PARAM = "metrics";
  private final ReportMetricBuilder reportMetricBuilder;

  @Inject
  public MetricsQueryParamAssembler(ReportMetricBuilder reportMetricBuilder) {
    this.reportMetricBuilder = reportMetricBuilder;
  }

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> metrics = params.get(METRICS_PARAM);
    List<ReportMetric> reportMetrics;

    if (metrics != null) {
      List<ReportMetrics> metricTypes =
          metrics.stream().map(ReportMetrics::get).collect(Collectors.toList());
      reportMetrics = reportMetricBuilder.someMetrics().withTypes(metricTypes).buildMany();
    } else {
      reportMetrics = reportMetricBuilder.someMetrics().buildMany();
    }

    return builder.withMetrics(reportMetrics);
  }
}
