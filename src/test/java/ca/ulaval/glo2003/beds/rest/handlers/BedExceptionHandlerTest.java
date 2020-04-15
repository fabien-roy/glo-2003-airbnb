package ca.ulaval.glo2003.beds.rest.handlers;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandlerTest;

public class BedExceptionHandlerTest extends AbstractExceptionHandlerTest<BedException> {

  public BedExceptionHandlerTest() {
    super(mock(BedException.class));
  }

  @Override
  protected void resetFactories() {
    exceptionHandler = new BedExceptionHandler(objectMapper, factories);
  }
}
