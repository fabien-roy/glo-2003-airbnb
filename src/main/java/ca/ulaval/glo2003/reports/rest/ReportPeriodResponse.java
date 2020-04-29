package ca.ulaval.glo2003.reports.rest;

import java.util.List;

public class ReportPeriodResponse {

  private String period;
  private List<ReportPeriodDataResponse> data;

  public ReportPeriodResponse(String period, List<ReportPeriodDataResponse> data) {
    this.period = period;
    this.data = data;
  }

  public String getPeriod() {
    return period;
  }

  public List<ReportPeriodDataResponse> getData() {
    return data;
  }
}
