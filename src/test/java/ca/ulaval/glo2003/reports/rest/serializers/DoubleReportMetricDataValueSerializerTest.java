package ca.ulaval.glo2003.reports.rest.serializers;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DoubleReportMetricDataValueSerializerTest extends ReportMetricDataValueSerializerTest {

  private static ReportMetricDataValueSerializer valueSerializer;

  @BeforeAll
  public static void setUpSerializer() {
    valueSerializer = new DoubleReportMetricDataValueSerializer();
  }

  @ParameterizedTest
  @ValueSource(doubles = {100.00, 100.0, 100})
  public void serialize_shouldFormatWithTwoDecimals(Double value) throws IOException {
    String expectedFormat = "100.00";

    valueSerializer.serialize(value, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }

  @ParameterizedTest
  @ValueSource(doubles = {100.12345, 100.1234, 100.123})
  public void serialize_withMoreThanTwoDecimals_shouldFormatWithTwoDecimals(Double value)
      throws IOException {
    String expectedFormat = "100.12";

    valueSerializer.serialize(value, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.12345, 00.12345, 0.123})
  public void serialize_withValueLowerThanOne_shouldFormatWithTwoDecimals(Double value)
      throws IOException {
    String expectedFormat = "0.12";

    valueSerializer.serialize(value, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }
}
