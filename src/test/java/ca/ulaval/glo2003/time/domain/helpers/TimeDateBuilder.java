package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.*;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDayBuilder.aTimeDay;

public class TimeDateBuilder {

  private TimeDayBuilder timeDayBuilder = aTimeDay();

  public static TimeDateBuilder aTimeDate() {
    return new TimeDateBuilder();
  }

  public TimeDateBuilder withYear(TimeYear year) {
    timeDayBuilder = timeDayBuilder.withYear(year);
    return this;
  }

  public TimeDateBuilder withQuarter(TimeQuarter quarter) {
    timeDayBuilder = timeDayBuilder.withQuarter(quarter);
    return this;
  }

  public TimeDateBuilder withMonth(TimeMonth month) {
    timeDayBuilder = timeDayBuilder.withMonth(month);
    return this;
  }

  public TimeDateBuilder withWeek(TimeWeek week) {
    timeDayBuilder = timeDayBuilder.withWeek(week);
    return this;
  }

  public TimeDate build() {
    return timeDayBuilder.build().toDate();
  }
}
