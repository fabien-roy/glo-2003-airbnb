package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeYear;

import java.time.Month;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimeYearBuilder.aTimeYear;

public class TimeMonthBuilder {

  private TimeYear year = null;
  private Month month = null;

  public static TimeMonthBuilder aTimeMonth() {
    return new TimeMonthBuilder();
  }

  public TimeMonthBuilder withYear(TimeYear year) {
    this.year = year;
    return this;
  }

  public TimeMonthBuilder withMonth(Month month) {
    this.month = month;
    return this;
  }

  public TimeMonth build() {
    if (year == null) year = aTimeYear().build();
    if (month == null) month = createMonth(year);

    return new TimeMonth(year, month);
  }
}
