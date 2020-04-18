package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.Timestamp;
import com.github.javafaker.Faker;
import java.time.Instant;

public class TimestampObjectMother {

  private TimestampObjectMother() {}

  public static Timestamp createTimestamp() {
    Instant value = Faker.instance().date().birthday().toInstant();
    return new Timestamp(value);
  }
}
