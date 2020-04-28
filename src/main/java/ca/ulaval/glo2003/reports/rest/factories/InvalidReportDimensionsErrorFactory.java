package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportDimensionsException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidReportDimensionsErrorFactory extends ReportErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidReportDimensionsException.class;
  }

  protected String getError() {
    return "INVALID_REPORT_DIMENSIONS";
  }

  protected String getDescription() {
    return "report dimensions should be one or many of package or lodgingMode";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
