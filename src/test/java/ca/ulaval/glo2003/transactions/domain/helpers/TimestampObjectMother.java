package ca.ulaval.glo2003.transactions.domain.helpers;

import com.github.javafaker.Faker;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampObjectMother {

  private TimestampObjectMother() {}

  public static LocalDateTime createValue() {
    return Faker.instance()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}
