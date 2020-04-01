package ca.ulaval.glo2003.transactions.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceDeserializerTest {

  private static PriceDeserializer priceDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static String mockPrice = "100.00";

  @BeforeAll
  public static void setUpDeserializer() {
    priceDeserializer = new PriceDeserializer();
  }

  @BeforeEach
  public void setUpPrice() throws IOException {
    mockPrice = "100.00";
    resetMocks();
  }

  private void resetMocks() throws IOException {
    reset(jsonParser);
    when(jsonParser.isNaN()).thenReturn(false);
    when(jsonParser.getText()).thenReturn(mockPrice);
    when(jsonParser.getDoubleValue()).thenReturn(Double.parseDouble(mockPrice));
  }

  @ParameterizedTest
  @ValueSource(strings = {"100.00", "100.0", "100"})
  public void deserialize_shouldGetPrice(String price) throws IOException {
    mockPrice = price;
    resetMocks();

    Double actualPrice = priceDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(Double.parseDouble(price), actualPrice);
  }

  @Test
  public void deserialize_withPriceWithMoreThanTwoDecimals_shouldThrowInvalidPackagesException()
      throws IOException {
    when(jsonParser.getText()).thenReturn("100.000");

    assertThrows(
        InvalidPackagesException.class,
        () -> priceDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  public void deserialize_withInvalidPrice_shouldThrowInvalidPackagesException()
      throws IOException {
    when(jsonParser.isNaN()).thenReturn(true);

    assertThrows(
        InvalidPackagesException.class,
        () -> priceDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
