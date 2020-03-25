package ca.ulaval.glo2003.beds.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceJsonSerializer extends JsonSerializer<Double> {

  // TODO : Use Deserializer instead of serializer...

  private NumberFormat decimalFormat;

  public PriceJsonSerializer() {
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    otherSymbols.setDecimalSeparator('.');
    decimalFormat = new DecimalFormat("#.00", otherSymbols);
  }

  @Override
  public void serialize(
      Double value, JsonGenerator generator, SerializerProvider serializerProvider)
      throws IOException {
    generator.writeRawValue(decimalFormat.format(value));
  }
}
