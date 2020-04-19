package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.admin.domain.ServiceFee;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportMetricBuilder {

  private List<ReportMetrics> metricTypes = new ArrayList<>();
  private ServiceFee serviceFee = null;

  public ReportMetricBuilder someMetrics() {
    return new ReportMetricBuilder();
  }

  public ReportMetricBuilder withTypes(List<ReportMetrics> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  // TODO : Test ReportMetricBuilder.withServiceFee()
  public ReportMetricBuilder withServiceFee(ServiceFee serviceFee) {
    this.serviceFee = serviceFee;
    return this;
  }

  public List<ReportMetric> buildMany() {
    return metricTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportMetric buildOne(ReportMetrics metricType) {
    switch (metricType) {
      case RESERVATIONS:
        return new ReservationsMetric();
      case CANCELATIONS:
        return new CancelationsMetric();
      default:
      case INCOMES:
        return new IncomesMetric(serviceFee);
    }
  }
}
