package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportMetricsException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidReportMetricsErrorFactory extends ReportErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidReportMetricsException.class;
  }

  protected String getError() {
    return "INVALID_REPORT_METRICS";
  }

  protected String getDescription() {
    return "report metrics should be one or many of incomes, reservations or cancelations";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
