package ca.ulaval.glo2003.transactions.domain.helpers;

import com.github.javafaker.Faker;
import java.time.Instant;

public class TimestampObjectMother {

  private TimestampObjectMother() {}

  public static Instant createValue() {
    return Faker.instance().date().birthday().toInstant();
  }
}
