package ca.ulaval.glo2003.time2.domain.helpers;

import com.github.javafaker.Faker;
import java.util.*;

public class CalendarHelper {

  public static int validMonthOfQuarter(int quarter) {
    return Faker.instance()
        .random()
        .nextInt(firstMonthOfQuarter(quarter), lastMonthOfQuarter(quarter));
  }

  private static int firstMonthOfQuarter(int quarter) {
    return (quarter * 3) - 3;
  }

  public static int firstMonthOfWeekOfYear(int year, int weekOfYear) {
    Calendar monthDay = thatMonthOfWeekOfYear(year, weekOfYear, Calendar.MONDAY);
    return monthDay.get(Calendar.MONTH);
  }

  public static int lastMonthOfWeekOfYear(int year, int weekOfYear) {
    Calendar monthDay = thatMonthOfWeekOfYear(year, weekOfYear, Calendar.SUNDAY);
    return monthDay.get(Calendar.MONTH);
  }

  private static Calendar thatMonthOfWeekOfYear(int year, int weekOfYear, int dayOfWeek) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.setWeekDate(year, weekOfYear, dayOfWeek);
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
    return calendar;
  }

  private static int lastMonthOfQuarter(int quarter) {
    return (quarter * 3) - 1;
  }

  public static int validDayOfMonth(int year, int month) {
    return Faker.instance()
        .random()
        .nextInt(firstDayOfMonth(year, month), lastDayOfMonth(year, month));
  }

  public static int firstDayOfMonth(int year, int month) {
    Calendar calendar = thatDayOfMonth(year, month);
    return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
  }

  public static int lastDayOfMonth(int year, int month) {
    Calendar calendar = thatDayOfMonth(year, month);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  private static Calendar thatDayOfMonth(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    return calendar;
  }

  public static int validDayOfMonthOfWeekOfYear(int year, int month, int weekOfYear) {
    int firstDayOfMonthOfWeekOfYear = firstDayOfMonthOfWeekOfYear(year, month, weekOfYear) + 1;
    int lastDayOfMonthOfWeekOfYear = lastDayOfMonthOfWeekOfYear(year, month, weekOfYear) - 1;
    return Faker.instance()
        .random()
        .nextInt(firstDayOfMonthOfWeekOfYear, lastDayOfMonthOfWeekOfYear);
  }

  public static int firstDayOfMonthOfWeekOfYear(int year, int month, int weekOfYear) {
    Calendar weekDay = thatDayOfMonthOfWeekOfYear(year, month, weekOfYear, Calendar.MONDAY);
    return weekDay.get(Calendar.DAY_OF_MONTH);
  }

  public static int lastDayOfMonthOfWeekOfYear(int year, int month, int weekOfYear) {
    Calendar weekDay = thatDayOfMonthOfWeekOfYear(year, month, weekOfYear, Calendar.SUNDAY);
    return weekDay.get(Calendar.DAY_OF_MONTH);
  }

  private static Calendar thatDayOfMonthOfWeekOfYear(
      int year, int month, int weekOfYear, int dayOfWeek) {
    Calendar calendar = thatMonthOfWeekOfYear(year, weekOfYear, dayOfWeek);
    calendar.set(Calendar.MONTH, month);
    return calendar;
  }
}
