package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class PriceConverter {

  public Price fromDouble(Double priceValue) {
    return new Price(BigDecimal.valueOf(priceValue));
  }

  public Double toDouble(Price price) {
    return price.getValue().doubleValue();
  }
}
