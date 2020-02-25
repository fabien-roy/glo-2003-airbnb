package ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

public class TransactionObjectMother {

  private TransactionObjectMother() {}

  public static LocalDate createTimestamp() {
    return Faker.instance()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public static String createFrom() {
    return Faker.instance().name().firstName();
  }

  public static String createTo() {
    return Faker.instance().name().firstName();
  }

  public static BigDecimal createTotal() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return BigDecimal.valueOf(randomDouble);
  }

  public static TransactionReasons createReason() {
    return randomEnum(TransactionReasons.class);
  }
}
