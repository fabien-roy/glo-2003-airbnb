package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimeQuarterBuilder.aTimeQuarter;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeQuarter;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.Month;

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

  public TimeMonth build() {
    if (quarter == null) quarter = aTimeQuarter().build();
    if (month == null) month = createMonth(quarter);

    return new TimeMonth(quarter, month);
  }
}
