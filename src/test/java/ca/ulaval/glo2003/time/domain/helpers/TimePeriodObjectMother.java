package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createDate;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import com.github.javafaker.Faker;

public class TimePeriodObjectMother {

  private TimePeriodObjectMother() {}

  public static TimePeriod createPeriod() {
    TimeDate startDate = createDate();
    return new TimePeriod(startDate, startDate.plusDays(createDays()));
  }

  private static int createDays() {
    return Faker.instance().random().nextInt(1, 30);
  }
}
