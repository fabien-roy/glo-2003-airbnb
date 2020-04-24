package ca.ulaval.glo2003.time2.domain.helpers;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateObjectMother.createDate;

import ca.ulaval.glo2003.time2.domain.TimeDate;
import ca.ulaval.glo2003.time2.domain.TimePeriod;
import java.time.LocalDate;

public class TimePeriodBuilder {

  public LocalDate DEFAULT_ZONED_DATE_TIME_START = createDate();
  public LocalDate DEFAULT_ZONED_DATE_TIME_END = createDate();

  public int startYear = DEFAULT_ZONED_DATE_TIME_START.getYear();
  public int startMonth = DEFAULT_ZONED_DATE_TIME_START.getMonthValue();
  public int dayOfMonthStart = DEFAULT_ZONED_DATE_TIME_START.getDayOfMonth();

  public int endYear = DEFAULT_ZONED_DATE_TIME_END.getYear();
  public int endMonth = DEFAULT_ZONED_DATE_TIME_END.getMonthValue();
  public int dayOfMonthEnd = DEFAULT_ZONED_DATE_TIME_END.getDayOfMonth();

  public static TimePeriodBuilder aTimePeriod() {
    return new TimePeriodBuilder();
  }

  public TimePeriodBuilder withYears(int startYear, int endYear) {
    this.startYear = startYear;
    this.endYear = endYear;
    return this;
  }

  public TimePeriod build() {
    TimeDate start =
        aTimeDate()
            .withYear(startYear)
            .withMonth(startMonth)
            .withDayOfMonth(dayOfMonthStart)
            .build();
    TimeDate end =
        aTimeDate().withYear(endYear).withMonth(endMonth).withDayOfMonth(dayOfMonthEnd).build();
    return start.isBefore(end) ? new TimePeriod(start, end) : new TimePeriod(end, start);
  }
}
