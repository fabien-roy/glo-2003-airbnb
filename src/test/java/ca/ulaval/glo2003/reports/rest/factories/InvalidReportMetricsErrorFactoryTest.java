package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportMetricsException;

class InvalidReportMetricsErrorFactoryTest extends ReportErrorFactoryTest {

  public InvalidReportMetricsErrorFactoryTest() {
    super(new InvalidReportMetricsException());
    errorFactory = new InvalidReportMetricsErrorFactory();
  }
}
