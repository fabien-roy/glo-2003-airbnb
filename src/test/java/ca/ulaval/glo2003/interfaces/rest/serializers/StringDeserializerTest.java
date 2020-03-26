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

class StringDeserializerTest {

  private static StringDeserializer stringDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static String value = "value";

  @BeforeAll
  public static void setUpDeserializer() {
    stringDeserializer = new FakeStringDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser);
    when(jsonParser.getText()).thenReturn(value);
  }

  @Test
  public void deserialize_shouldReturnString() {
    String actualValue = stringDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(value, actualValue);
  }

  @Test
  public void deserialize_withInvalidString_shouldThrowException() throws IOException {
    when(jsonParser.getText()).thenThrow(new IOException());

    assertThrows(
        TestingException.class,
        () -> stringDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
