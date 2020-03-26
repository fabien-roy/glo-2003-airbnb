package ca.ulaval.glo2003.interfaces.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.exceptions.TestingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerDeserializerTest {

  private static IntegerDeserializer integerDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static int integer = 1;

  @BeforeAll
  public static void setUpDeserializer() {
    integerDeserializer = new FakeIntegerDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser);
    when(jsonParser.getIntValue()).thenReturn(integer);
  }

  @Test
  public void deserialize_shouldReturnInteger() {
    int actualInteger = integerDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(integer, actualInteger);
  }

  @Test
  public void deserialize_withInvalidInteger_shouldThrowException() throws IOException {
    when(jsonParser.getIntValue()).thenThrow(new IOException());

    assertThrows(
        TestingException.class,
        () -> integerDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
