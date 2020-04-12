package ca.ulaval.glo2003.transactions.domain.helpers;

import ca.ulaval.glo2003.transactions.domain.Price;
import com.github.javafaker.Faker;

public class PriceObjectMother {

  private PriceObjectMother() {}

  public static Price createPrice() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return new Price(randomDouble);
  }
}
