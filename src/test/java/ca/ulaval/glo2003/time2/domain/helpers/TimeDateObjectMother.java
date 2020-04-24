package ca.ulaval.glo2003.time2.domain.helpers;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeDateObjectMother {

  private TimeDateObjectMother() {}

  public static LocalDate createDate() {
    return toLocalDate(Faker.instance().date().future(30, 1, TimeUnit.DAYS));
  }

  private static LocalDate toLocalDate(Date date) {
    return date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
  }
}
