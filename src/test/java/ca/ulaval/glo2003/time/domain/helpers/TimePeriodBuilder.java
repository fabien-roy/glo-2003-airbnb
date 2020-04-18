package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time.domain.helpers.TimePeriodObjectMother.createNumberOfDays;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;

public class TimePeriodBuilder {

  private TimeDate start = aTimeDate().build();
  private int numberOfDays = createNumberOfDays();
  private TimeDate end = start.plusDays(numberOfDays);

  public static TimePeriodBuilder aTimePeriod() {
    return new TimePeriodBuilder();
  }

  public TimePeriodBuilder withinYear(TimeYear year) {
    return withinYears(year, year);
  }

  public TimePeriodBuilder withinYears(TimeYear firstYear, TimeYear secondYear) {
    TimeDate firstDate = aTimeDate().withYear(firstYear).build();
    TimeDate secondDate = aTimeDate().withYear(secondYear).build();
    setPeriodDates(firstDate, secondDate);
    return this;
  }

  public TimePeriodBuilder withinMonth(TimeMonth month) {
    return withinMonths(month, month);
  }

  public TimePeriodBuilder withinMonths(TimeMonth firstMonth, TimeMonth secondMonth) {
    TimeDate firstDate = aTimeDate().withMonth(firstMonth).build();
    TimeDate secondDate = aTimeDate().withMonth(secondMonth).build();
    setPeriodDates(firstDate, secondDate);
    return this;
  }

  public TimePeriod build() {
    return new TimePeriod(start, end);
  }

  private void setPeriodDates(TimeDate firstDate, TimeDate secondDate) {
    if (firstDate.isBefore(secondDate)) {
      start = firstDate;
      end = secondDate;
    } else {
      start = secondDate;
      end = firstDate;
    }
  }
}
