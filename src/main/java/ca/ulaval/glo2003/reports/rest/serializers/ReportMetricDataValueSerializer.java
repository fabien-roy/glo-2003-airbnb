package ca.ulaval.glo2003.reports.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class ReportMetricDataValueSerializer extends AbstractSerializer<Double> {

  public ReportMetricDataValueSerializer() {
    super(Double.class);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  protected abstract NumberFormat getNumberFormat();

  protected DecimalFormatSymbols getOtherSymbols() {
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    otherSymbols.setDecimalSeparator('.');
    return otherSymbols;
  }

  @Override
  public void serialize(
      Double value, JsonGenerator generator, SerializerProvider serializerProvider)
      throws IOException {
    generator.writeRawValue(getNumberFormat().format(value));
  }
}
