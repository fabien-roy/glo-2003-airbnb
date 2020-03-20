package ca.ulaval.glo2003.transactions.rest.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import com.github.javafaker.Faker;
import java.time.ZoneId;

public class TransactionResponseObjectMother {

  private TransactionResponseObjectMother() {}

  public static String createTimestamp() {
    return Faker.instance()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
        .toString();
  }

  public static String createFrom() {
    return Faker.instance().name().firstName();
  }

  public static String createTo() {
    return Faker.instance().name().firstName();
  }

  public static Double createTotal() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }

  public static String createReason() {
    return randomEnum(TransactionReasons.class).toString();
  }
}
