package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportDimensionsException;

class InvalidReportDimensionsErrorFactoryTest extends ReportErrorFactoryTest {

  public InvalidReportDimensionsErrorFactoryTest() {
    super(new InvalidReportDimensionsException());
    errorFactory = new InvalidReportDimensionsErrorFactory();
  }
}
