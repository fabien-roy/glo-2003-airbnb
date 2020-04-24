package ca.ulaval.glo2003.time2.domain.helpers;

import static ca.ulaval.glo2003.time2.domain.helpers.ZonedDateTimeObjectMother.createZonedDateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedDateTimeBuilder {

  public ZonedDateTime DEFAULT_ZONED_DATE_TIME = createZonedDateTime();

  public int year = DEFAULT_ZONED_DATE_TIME.getYear();
  public int month = DEFAULT_ZONED_DATE_TIME.getMonthValue();
  public int dayOfMonth = DEFAULT_ZONED_DATE_TIME.getDayOfMonth();

  public static ZonedDateTimeBuilder aZonedDateTime() {
    return new ZonedDateTimeBuilder();
  }

  public ZonedDateTimeBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public ZonedDateTime build() {
    return LocalDate.of(year, month, dayOfMonth).atTime(LocalTime.MIDNIGHT).atZone(ZoneOffset.UTC);
  }
}
