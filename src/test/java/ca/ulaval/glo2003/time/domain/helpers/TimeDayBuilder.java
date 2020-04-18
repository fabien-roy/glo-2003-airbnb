package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.*;
import static ca.ulaval.glo2003.time.domain.helpers.TimeMonthBuilder.aTimeMonth;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.time.domain.TimeDay;
import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeWeek;
import ca.ulaval.glo2003.time.domain.TimeYear;

public class TimeDayBuilder {

  private TimeMonth month = null;
  private TimeWeek week = mock(TimeWeek.class); // TODO : TimeDayBuilder.week
  private int dayOfYear = -1;
  private int dayOfMonth = -1;
  private int dayOfWeek = 1; // TODO : TimeDayBuilder.dayOfWeek

  public static TimeDayBuilder aTimeDay() {
    return new TimeDayBuilder();
  }

  public TimeDayBuilder withYear(TimeYear year) {
    month = aTimeMonth().withYear(year).build();
    return this;
  }

  public TimeDay build() {
    if (month == null) month = aTimeMonth().build();
    if (dayOfMonth == -1) dayOfMonth = createDayOfMonth(month);
    if (dayOfYear == -1) dayOfYear = createDayOfYear(month, dayOfMonth);

    return new TimeDay(month, week, dayOfYear, dayOfMonth, dayOfWeek);
  }
}
