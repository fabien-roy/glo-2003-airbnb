package ca.ulaval.glo2003.reports.rest.handlers;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandlerTest;
import ca.ulaval.glo2003.reports.exceptions.ReportException;

public class ReportExceptionHandlerTest extends AbstractExceptionHandlerTest<ReportException> {

  public ReportExceptionHandlerTest() {
    super(mock(ReportException.class));
  }

  @Override
  protected void resetFactories() {
    exceptionHandler = new ReportExceptionHandler(objectMapper, factories);
  }
}
