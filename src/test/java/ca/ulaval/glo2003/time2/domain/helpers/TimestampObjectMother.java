package ca.ulaval.glo2003.time2.domain.helpers;

import com.github.javafaker.Faker;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimestampObjectMother {

  private TimestampObjectMother() {}

  public static Instant createInstant() {
    return Faker.instance().date().future(30, 1, TimeUnit.DAYS).toInstant();
  }
}
