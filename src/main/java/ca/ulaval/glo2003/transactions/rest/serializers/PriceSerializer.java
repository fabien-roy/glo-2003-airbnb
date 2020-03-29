package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceSerializer extends AbstractSerializer<Double> {

  private NumberFormat decimalFormat;

  public PriceSerializer() {
    super(Double.class);
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    otherSymbols.setDecimalSeparator('.');
    decimalFormat = new DecimalFormat("#.00", otherSymbols);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  @Override
  public void serialize(
      Double value, JsonGenerator generator, SerializerProvider serializerProvider)
      throws IOException {
    generator.writeRawValue(decimalFormat.format(value));
  }
}
