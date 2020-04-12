package ca.ulaval.glo2003.transactions.domain.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Timestamp;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import com.github.javafaker.Faker;
import java.time.Instant;

public class TransactionObjectMother {

  private TransactionObjectMother() {}

  public static Timestamp createTimestamp() {
    Instant value = Faker.instance().date().birthday().toInstant();
    return new Timestamp(value);
  }

  public static String createFrom() {
    return Faker.instance().name().firstName();
  }

  public static String createTo() {
    return Faker.instance().name().firstName();
  }

  public static Price createTotal() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return new Price(randomDouble);
  }

  public static TransactionReasons createReason() {
    return randomEnum(TransactionReasons.class);
  }
}
