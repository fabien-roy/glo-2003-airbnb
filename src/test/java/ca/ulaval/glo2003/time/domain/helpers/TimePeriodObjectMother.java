package ca.ulaval.glo2003.time.domain.helpers;

import com.github.javafaker.Faker;

public class TimePeriodObjectMother {

  private TimePeriodObjectMother() {}

  public static int createNumberOfDays() {
    return Faker.instance().random().nextInt(1, 30);
  }
}
