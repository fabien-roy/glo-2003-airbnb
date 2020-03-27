package ca.ulaval.glo2003.interfaces.rest.serializers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.exceptions.TestingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringArrayDeserializerTest {

  private static StringArrayDeserializer stringArrayDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static String firstString = Faker.instance().dragonBall().character();
  private static String secondString = Faker.instance().dragonBall().character();

  @BeforeAll
  public static void setUpDeserializer() {
    stringArrayDeserializer = new FakeStringArrayDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser);
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken())
        .thenReturn(JsonToken.VALUE_STRING)
        .thenReturn(JsonToken.VALUE_STRING)
        .thenReturn(JsonToken.END_ARRAY);
    when(jsonParser.getValueAsString()).thenReturn(firstString).thenReturn(secondString);
  }

  @Test
  public void deserialize_shouldReturnStringArray() {
    List<String> strings =
        Arrays.asList(stringArrayDeserializer.deserialize(jsonParser, deserializationContext));

    assertEquals(2, strings.size());
    assertTrue(strings.contains(firstString));
    assertTrue(strings.contains(secondString));
  }

  @Test
  public void deserialize_withInvalidArray_shouldThrowException() {
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.NOT_AVAILABLE);

    assertThrows(
        TestingException.class,
        () -> stringArrayDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  public void deserialize_withInvalidValue_shouldThrowException() throws IOException {
    when(jsonParser.getValueAsString()).thenThrow(new IOException());

    assertThrows(
        TestingException.class,
        () -> stringArrayDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  public void deserialize_withEmptyArray_shouldThrowException() throws IOException {
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken()).thenReturn(JsonToken.END_ARRAY);

    assertThrows(
        TestingException.class,
        () -> stringArrayDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
