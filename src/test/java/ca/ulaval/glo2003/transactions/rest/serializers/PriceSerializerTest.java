package ca.ulaval.glo2003.transactions.rest.serializers;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceSerializerTest {

  private static PriceSerializer priceSerializer;
  private static JsonGenerator jsonGenerator = mock(JsonGenerator.class);
  private static SerializerProvider serializerProvider = mock(SerializerProvider.class);

  @BeforeAll
  public static void setUpDeserializer() {
    priceSerializer = new PriceSerializer();
  }

  @BeforeEach
  public void resetMocks() {
    reset(jsonGenerator);
  }

  @ParameterizedTest
  @ValueSource(doubles = {100.00, 100.0, 100})
  public void serialize_shouldFormatWithTwoDecimals(Double price) throws IOException {
    String expectedFormat = "100.00";

    priceSerializer.serialize(price, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }

  @ParameterizedTest
  @ValueSource(doubles = {100.12345, 100.1234, 100.123})
  public void serialize_withMoreThanTwoDecimals_shouldFormatWithTwoDecimals(Double price)
      throws IOException {
    String expectedFormat = "100.12";

    priceSerializer.serialize(price, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.12345, 00.12345, 0.123})
  public void serialize_withValueLowerThanOne_shouldFormatWithTwoDecimals(Double price)
      throws IOException {
    String expectedFormat = "0.12";

    priceSerializer.serialize(price, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }
}
