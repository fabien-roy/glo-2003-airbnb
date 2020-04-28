package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.CalendarHelper.*;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createDate;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
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

  public TimePeriodBuilder withQuarters(int firstQuarter, int secondQuarter) {
    this.startMonth = toJavaTimeMonth(validMonthOfQuarter(firstQuarter));
    this.endMonth = toJavaTimeMonth(validMonthOfQuarter(secondQuarter));
    this.dayOfMonthStart = firstDayOfMonth(endYear, toJavaCalendarMonth(startMonth));
    this.dayOfMonthEnd = lastDayOfMonth(endYear, toJavaCalendarMonth(endMonth));
    return this;
  }

  public TimePeriodBuilder withMonth(int month) {
    return withMonths(month, month);
  }

  public TimePeriodBuilder withMonths(int startMonth, int endMonth) {
    this.startMonth = startMonth;
    this.endMonth = endMonth;
    this.dayOfMonthStart = firstDayOfMonth(endYear, toJavaCalendarMonth(startMonth));
    this.dayOfMonthEnd = lastDayOfMonth(endYear, toJavaCalendarMonth(endMonth));
    return this;
  }

  public TimePeriodBuilder withWeeks(int startWeekOfYear, int endWeekOfYear) {
    this.dayOfMonthStart =
        validDayOfMonthOfWeekOfYear(startYear, toJavaCalendarMonth(startMonth), startWeekOfYear);
    this.dayOfMonthEnd =
        validDayOfMonthOfWeekOfYear(endYear, toJavaCalendarMonth(endMonth), endWeekOfYear);
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
