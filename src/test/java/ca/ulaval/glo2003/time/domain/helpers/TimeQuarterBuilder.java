package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createQuarterOfYear;
import static ca.ulaval.glo2003.time.domain.helpers.TimeYearBuilder.aTimeYear;

import ca.ulaval.glo2003.time.domain.TimeQuarter;
import ca.ulaval.glo2003.time.domain.TimeYear;

public class TimeQuarterBuilder {

  private TimeYear year = null;
  private int quarterOfYear = -1;

  public static TimeQuarterBuilder aTimeQuarter() {
    return new TimeQuarterBuilder();
  }

  public TimeQuarterBuilder withQuarterOfYear(int quarterOfYear) {
    this.quarterOfYear = quarterOfYear;
    return this;
  }

  public TimeQuarterBuilder withYear(TimeYear year) {
    this.year = year;
    return this;
  }

  public TimeQuarter build() {
    if (year == null) year = aTimeYear().build();
    if (quarterOfYear == -1) quarterOfYear = createQuarterOfYear(year);

    return new TimeQuarter(year, quarterOfYear);
  }
}
