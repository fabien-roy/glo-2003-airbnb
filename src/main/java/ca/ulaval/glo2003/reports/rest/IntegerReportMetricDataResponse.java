package ca.ulaval.glo2003.reports.rest;

import ca.ulaval.glo2003.reports.rest.serializers.IntegerReportMetricDataValueSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IntegerReportMetricDataResponse extends ReportMetricDataResponse {

  @JsonSerialize(using = IntegerReportMetricDataValueSerializer.class)
  private double value;

  public IntegerReportMetricDataResponse(String name, double value) {
    super(name);
    this.value = value;
  }

  @Override
  public double getValue() {
    return value;
  }
}
