package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createWeekOfYear;
import static ca.ulaval.glo2003.time.domain.helpers.TimeYearBuilder.aTimeYear;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeWeek;
import ca.ulaval.glo2003.time.domain.TimeYear;

public class TimeWeekBuilder {

  private TimeYear year = null;
  private int weekOfYear = -1;

  public static TimeWeekBuilder aTimeWeek() {
    return new TimeWeekBuilder();
  }

  public TimeWeekBuilder withYear(TimeYear year) {
    this.year = year;
    return this;
  }

  public TimeWeekBuilder withMonth(TimeMonth month) {
    year = month.getYear();
    weekOfYear = createWeekOfYear(month);
    return this;
  }

  public TimeWeek build() {
    if (year == null) year = aTimeYear().build();
    if (weekOfYear == -1) weekOfYear = createWeekOfYear(year);

    return new TimeWeek(year, weekOfYear);
  }
}
