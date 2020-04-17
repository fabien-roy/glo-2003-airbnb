package ca.ulaval.glo2003.reports.rest.factories;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportScopeException;
import org.eclipse.jetty.http.HttpStatus;

// TODO : Test InvalidReportScopeErrorFactory
public class InvalidReportScopeErrorFactory extends ReportErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidReportScopeException.class;
  }

  protected String getError() {
    return "INVALID_REPORT_SCOPE";
  }

  protected String getDescription() {
    return "report scope should be one of weekly, monthly, quarterly or annual";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
