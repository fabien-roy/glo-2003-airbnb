package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportScopeException;

class InvalidReportScopeErrorFactoryTest extends ReportErrorFactoryTest {

  public InvalidReportScopeErrorFactoryTest() {
    super(new InvalidReportScopeException());
    errorFactory = new InvalidReportScopeErrorFactory();
  }
}
