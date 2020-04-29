package ca.ulaval.glo2003.reports.converters;

import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.reports.rest.DoubleReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.IntegerReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportMetricDataResponse;
import java.util.ArrayList;
import java.util.List;

public class ReportMetricDataConverter {

  public List<ReportMetricDataResponse> toResponses(List<ReportMetricData> metrics) {
    List<ReportMetricDataResponse> responses = new ArrayList<>();
    metrics.forEach(metric -> responses.add(toResponse(metric)));
    return responses;
  }

  private ReportMetricDataResponse toResponse(ReportMetricData data) {
    String name = data.getName().toString();
    double value = data.getValue().doubleValue();

    switch (data.getFormat()) {
      case DOUBLE:
        return new DoubleReportMetricDataResponse(name, value);
      default:
      case INTEGER:
        return new IntegerReportMetricDataResponse(name, value);
    }
  }
}
