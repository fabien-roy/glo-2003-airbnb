package ca.ulaval.glo2003.errors.rest.factories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

class JsonParsingErrorFactoryTest extends CatchallErrorFactoryTest {

  public JsonParsingErrorFactoryTest() {
    super(mock(JsonProcessingException.class));
    errorFactory = new JsonParsingErrorFactory();
  }

  @Test
  public void canHandle_withoutUnrelatedException_shouldReturnFalse() {
    Exception unrelatedException = new Exception();

    boolean result = errorFactory.canHandle(unrelatedException);

    assertFalse(result);
  }
}
