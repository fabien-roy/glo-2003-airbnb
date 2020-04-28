package ca.ulaval.glo2003.transactions.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import com.github.javafaker.Faker;

public class TransactionObjectMother {

  private TransactionObjectMother() {}

  public static String createFrom() {
    return Faker.instance().name().firstName();
  }

  public static String createTo() {
    return Faker.instance().name().firstName();
  }

  public static Price createTotal() {
    return createPrice();
  }

  public static TransactionReasons createReason() {
    return randomEnum(TransactionReasons.class);
  }
}
