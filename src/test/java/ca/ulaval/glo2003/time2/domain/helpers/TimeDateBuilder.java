package ca.ulaval.glo2003.time2.domain.helpers;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateObjectMother.createDate;

import ca.ulaval.glo2003.time2.domain.TimeDate;
import java.time.LocalDate;

public class TimeDateBuilder {

  public LocalDate DEFAULT_ZONED_DATE_TIME = createDate();

  public int year = DEFAULT_ZONED_DATE_TIME.getYear();
  public int month = DEFAULT_ZONED_DATE_TIME.getMonthValue();
  public int dayOfMonth = DEFAULT_ZONED_DATE_TIME.getDayOfMonth();

  public static TimeDateBuilder aTimeDate() {
    return new TimeDateBuilder();
  }

  public TimeDateBuilder withDate(LocalDate date) {
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.dayOfMonth = date.getDayOfMonth();
    return this;
  }

  public TimeDateBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public TimeDateBuilder withMonth(int month) {
    this.month = month;
    return this;
  }

  public TimeDateBuilder withDayOfMonth(int dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
    return this;
  }

  public TimeDate build() {
    LocalDate date = LocalDate.of(year, month, dayOfMonth);
    return new TimeDate(date);
  }
}
