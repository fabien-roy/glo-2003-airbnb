package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time.domain.helpers.TimePeriodObjectMother.createNumberOfDays;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;

public class TimePeriodBuilder {

  private TimeDate start = aTimeDate().build();
  private int numberOfDays = createNumberOfDays();
  private TimeDate end = start.plusDays(numberOfDays);

  public static TimePeriodBuilder aTimePeriod() {
    return new TimePeriodBuilder();
  }

  public TimePeriod build() {
    return new TimePeriod(start, end);
  }
}
