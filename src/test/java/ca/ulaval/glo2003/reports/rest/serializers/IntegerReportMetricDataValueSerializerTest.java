package ca.ulaval.glo2003.reports.rest.serializers;

import static org.mockito.Mockito.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IntegerReportMetricDataValueSerializerTest extends ReportMetricDataValueSerializerTest {

  private static ReportMetricDataValueSerializer valueSerializer;

  @BeforeAll
  public static void setUpSerializer() {
    valueSerializer = new IntegerReportMetricDataValueSerializer();
  }

  @ParameterizedTest
  @ValueSource(doubles = {100.00, 100.0, 100})
  public void serialize_shouldFormatWithoutDecimal(Double value) throws IOException {
    String expectedFormat = "100";

    valueSerializer.serialize(value, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.12345, 00.12345, 0.123})
  public void serialize_withValueLowerThanOne_shouldFormatWithoutDecimals(Double value)
      throws IOException {
    String expectedFormat = "0";

    valueSerializer.serialize(value, jsonGenerator, serializerProvider);

    verify(jsonGenerator).writeRawValue(expectedFormat);
  }
}
