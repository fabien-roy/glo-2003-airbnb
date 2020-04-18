package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.TimeDate;
import com.github.javafaker.Faker;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class TimeDateObjectMother {

  private TimeDateObjectMother() {}

  public static TimeDate createDate() {
    return new TimeDate(
        Faker.instance()
            .date()
            .between(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(31)))
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
  }
}
