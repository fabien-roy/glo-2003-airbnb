package ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.domain.Price;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TransactionObjectMother {

  private TransactionObjectMother() {}

  public static LocalDateTime createTimestamp() {
    return Faker.instance()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

  public static String createFrom() {
    return Faker.instance().name().firstName();
  }

  public static String createTo() {
    return Faker.instance().name().firstName();
  }

  public static Price createTotal() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return new Price(BigDecimal.valueOf(randomDouble));
  }

  public static TransactionReasons createReason() {
    return randomEnum(TransactionReasons.class);
  }
}
