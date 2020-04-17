package ca.ulaval.glo2003.reports.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import ca.ulaval.glo2003.reports.exceptions.ReportException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

// TODO : Test ReportExceptionHandler
public class ReportExceptionHandler extends AbstractExceptionHandler<ReportException> {

  private final Set<ErrorFactory<ReportException>> factories;

  @Inject
  public ReportExceptionHandler(
      ObjectMapper objectMapper, Set<ErrorFactory<ReportException>> factories) {
    super(objectMapper);
    this.factories = factories;
  }

  @Override
  public void handle(ReportException e, Request request, Response response) {
    handleIfCan(factories, e, response);
  }
}
