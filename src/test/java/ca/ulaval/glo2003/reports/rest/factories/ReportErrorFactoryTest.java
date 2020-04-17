package ca.ulaval.glo2003.reports.rest.factories;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactoryTest;
import ca.ulaval.glo2003.reports.exceptions.ReportException;

public abstract class ReportErrorFactoryTest extends AbstractErrorFactoryTest<ReportException> {

  protected ReportErrorFactoryTest(ReportException associatedException) {
    super(associatedException, mock(ReportException.class));
  }
}
