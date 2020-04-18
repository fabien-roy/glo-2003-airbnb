package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeQuarter;
import ca.ulaval.glo2003.time.domain.TimeWeek;
import ca.ulaval.glo2003.time.domain.TimeYear;

import java.time.Month;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimeQuarterBuilder.aTimeQuarter;

public class TimeMonthBuilder {

  private TimeQuarter quarter = null;
  private Month month = null;

  public static TimeMonthBuilder aTimeMonth() {
    return new TimeMonthBuilder();
  }

  public TimeMonthBuilder withYear(TimeYear year) {
    quarter = aTimeQuarter().withYear(year).build();
    return this;
  }

  public TimeMonthBuilder withQuarter(TimeQuarter quarter) {
    this.quarter = quarter;
    return this;
  }

  public TimeMonthBuilder withWeek(TimeWeek week) {
    month = createMonth(week);
    quarter = aTimeQuarter().withYear(week.getYear()).withQuarterOfYear(month.getValue() / 3 * 3).build();
    return this;
  }

  public TimeMonth build() {
    if (quarter == null) quarter = aTimeQuarter().build();
    if (month == null) month = createMonth(quarter);

    return new TimeMonth(quarter, month);
  }
}
