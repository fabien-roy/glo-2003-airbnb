package ca.ulaval.glo2003.reports.rest;

import ca.ulaval.glo2003.reports.rest.serializers.DoubleReportMetricDataValueSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DoubleReportMetricDataResponse extends ReportMetricDataResponse {

  @JsonSerialize(using = DoubleReportMetricDataValueSerializer.class)
  private double value;

  public DoubleReportMetricDataResponse(String name, double value) {
    super(name);
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
