package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimeYear;

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

  public TimeDate build() {
    return timeDayBuilder.build().toDate();
  }
}
