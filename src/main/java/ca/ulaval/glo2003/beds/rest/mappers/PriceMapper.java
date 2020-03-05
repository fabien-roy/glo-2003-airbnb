package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Price;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceMapper {

  private NumberFormat decimalFormat;

  public PriceMapper() {
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    otherSymbols.setDecimalSeparator('.');
    decimalFormat = new DecimalFormat("#.00", otherSymbols);
  }

  public Price fromDouble(double priceValue) {
    return new Price(BigDecimal.valueOf(priceValue));
  }

  public double toDouble(Price price) {
    String formattedDouble = decimalFormat.format(price.getValue().doubleValue());
    return Double.parseDouble(formattedDouble);
  }
}
