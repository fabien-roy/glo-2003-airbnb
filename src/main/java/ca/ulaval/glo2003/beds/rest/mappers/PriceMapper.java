package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Price;
import java.math.BigDecimal;

public class PriceMapper {

  public Price fromDouble(Double priceValue) {
    return new Price(BigDecimal.valueOf(priceValue));
  }

  public Double toDouble(Price price) {
    return price.getValue().doubleValue();
  }
}
