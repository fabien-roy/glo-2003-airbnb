package ca.ulaval.glo2003.errors.rest.factories;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class DefaultErrorFactoryTest extends CatchallErrorFactoryTest {

  public DefaultErrorFactoryTest() {
    super(mock(Exception.class));
    errorFactory = new DefaultErrorFactory();
  }

  @Test
  public void canHandle_withoutAnyException_shouldReturnTrue() {
    Exception exception = new Exception();

    boolean result = errorFactory.canHandle(exception);

    assertTrue(result);
  }
}
