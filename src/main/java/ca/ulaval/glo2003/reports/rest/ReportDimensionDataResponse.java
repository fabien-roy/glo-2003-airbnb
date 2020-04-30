package ca.ulaval.glo2003.reports.rest;

public class ReportDimensionDataResponse {

  private String name;
  private String value;

  public ReportDimensionDataResponse(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }
}
