package ca.ulaval.glo2003.reports.rest.serializers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;

public abstract class ReportMetricDataValueSerializerTest {

  protected static JsonGenerator jsonGenerator = mock(JsonGenerator.class);
  protected static SerializerProvider serializerProvider = mock(SerializerProvider.class);

  @BeforeEach
  public void resetMocks() {
    reset(jsonGenerator);
  }
}
