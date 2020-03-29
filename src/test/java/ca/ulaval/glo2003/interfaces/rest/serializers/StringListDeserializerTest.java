package ca.ulaval.glo2003.interfaces.rest.serializers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.exceptions.TestingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringListDeserializerTest {

  private static StringListDeserializer stringListDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static String firstString = "firstString";
  private static String secondString = "secondString";

  @BeforeAll
  public static void setUpDeserializer() {
    stringListDeserializer = new FakeStringListDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser);
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken())
        .thenReturn(JsonToken.VALUE_STRING)
        .thenReturn(JsonToken.VALUE_STRING)
        .thenReturn(JsonToken.END_ARRAY);
    when(jsonParser.getValueAsString()).thenReturn(firstString, secondString);
  }

  @Test
  public void deserialize_withSingleString_shouldGetString() throws IOException {
    when(jsonParser.nextToken()).thenReturn(JsonToken.VALUE_STRING).thenReturn(JsonToken.END_ARRAY);

    List<String> strings = stringListDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(1, strings.size());
    assertEquals(firstString, strings.get(0));
  }

  @Test
  public void deserialize_withMultipleStrings_shouldGetStrings() {
    List<String> strings = stringListDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(2, strings.size());
    assertTrue(strings.contains(firstString));
    assertTrue(strings.contains(secondString));
  }

  @Test
  public void deserialize_withInvalidString_shouldThrowException() throws IOException {
    when(jsonParser.getValueAsString()).thenThrow(new IOException());

    assertThrows(
        TestingException.class,
        () -> stringListDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
