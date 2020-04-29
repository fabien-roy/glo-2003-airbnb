package ca.ulaval.glo2003.reports.converters;

import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.reports.rest.ReportMetricDataResponse;
import java.util.ArrayList;
import java.util.List;

// TODO : Test this!
public class ReportMetricDataConverter {

  public List<ReportMetricDataResponse> toResponses(List<ReportMetricData> metrics) {
    List<ReportMetricDataResponse> responses = new ArrayList<>();
    metrics.forEach(metric -> responses.add(toResponse(metric)));
    return responses;
  }

  private ReportMetricDataResponse toResponse(ReportMetricData data) {
    return null; // TODO
  }
}
