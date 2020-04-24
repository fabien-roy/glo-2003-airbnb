package ca.ulaval.glo2003.time2.domain.helpers;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDayObjectMother.createZonedDateTime;

import ca.ulaval.glo2003.time2.domain.TimeDay;
import java.time.ZonedDateTime;

public class TimeDayBuilder {

  private static ZonedDateTime DEFAULT_ZONED_DATE_TIME = createZonedDateTime();
  private static ZonedDateTime zonedDateTime = DEFAULT_ZONED_DATE_TIME;

  public static TimeDayBuilder aDay() {
    return new TimeDayBuilder();
  }

  public TimeDayBuilder withZonedDateTime(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
    return this;
  }

  public TimeDay build() {
    return new TimeDay(zonedDateTime);
  }
}
