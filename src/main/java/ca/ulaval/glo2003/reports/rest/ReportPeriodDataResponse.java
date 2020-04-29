package ca.ulaval.glo2003.reports.rest;

import java.util.List;

public class ReportPeriodDataResponse {

  private List<ReportDimensionDataResponse> dimensions;
  private List<ReportMetricDataResponse> metrics;

  public ReportPeriodDataResponse(
      List<ReportDimensionDataResponse> dimensions, List<ReportMetricDataResponse> metrics) {
    this.dimensions = dimensions;
    this.metrics = metrics;
  }

  public List<ReportDimensionDataResponse> getDimensions() {
    return dimensions;
  }

  public List<ReportMetricDataResponse> getMetrics() {
    return metrics;
  }
}
