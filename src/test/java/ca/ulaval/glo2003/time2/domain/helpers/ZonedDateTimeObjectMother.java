package ca.ulaval.glo2003.time2.domain.helpers;

import com.github.javafaker.Faker;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class ZonedDateTimeObjectMother {

  private ZonedDateTimeObjectMother() {}

  public static ZonedDateTime createZonedDateTime() {
    return Faker.instance().date().future(30, 1, TimeUnit.DAYS).toInstant().atZone(ZoneOffset.UTC);
  }
}
