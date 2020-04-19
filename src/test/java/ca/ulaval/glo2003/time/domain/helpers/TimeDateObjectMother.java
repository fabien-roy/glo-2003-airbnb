package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.TimeQuarter.firstPossibleQuarter;
import static ca.ulaval.glo2003.time.domain.TimeQuarter.lastPossibleQuarter;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeQuarter;
import ca.ulaval.glo2003.time.domain.TimeWeek;
import ca.ulaval.glo2003.time.domain.TimeYear;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;

public class TimeDateObjectMother {

  private TimeDateObjectMother() {}

  private static int randomBetween(int firstBound, int secondBound) {
    if (firstBound == secondBound) return firstBound;
    int lowerBound = Math.min(firstBound, secondBound);
    int higherBound = Math.max(firstBound, secondBound);
    return Faker.instance().random().nextInt(lowerBound, higherBound);
  }

  public static Year createYear() {
    return Year.of(Faker.instance().random().nextInt(thisYear(), inManyYears()));
  }

  public static int createQuarterOfYear(TimeYear year) {
    return randomBetween(futureQuarter(year), lastPossibleQuarter());
  }

  public static int createQuarterOfYear(TimeWeek week) {
    return randomBetween(futureQuarter(week), week.lastQuarter());
  }

  public static Month createMonth(TimeQuarter quarter) {
    return Month.of(randomBetween(futureMonth(quarter), quarter.lastMonth()));
  }

  public static Month createMonth(TimeWeek week) {
    return Month.of(randomBetween(futureMonth(week), week.lastMonth()));
  }

  public static int createWeekOfYear(TimeYear year) {
    return Faker.instance().random().nextInt(1, year.getNumberOfWeeks());
  }

  public static int createWeekOfYear(TimeMonth month) {
    return randomBetween(month.firstWeek(), month.lastWeek());
  }

  public static int createDayOfYear(TimeMonth month, int dayOfMonth) {
    return randomBetween(thatDay(month, dayOfMonth), month.getYear().atLastDay().getDayOfYear());
  }

  public static int createDayOfMonth(TimeMonth month, TimeWeek week) {
    int lowerBound = Math.max(futureDay(month), futureDay(week));
    int higherBound = Math.min(month.atLastDay().getDayOfMonth(), week.atLastDay().getDayOfMonth());
    return randomBetween(lowerBound, higherBound);
  }

  public static int createDayOfWeek(TimeWeek week, int dayOfYear) {
    return week.getYear().toYear().atDay(dayOfYear).getDayOfWeek().getValue();
  }

  private static LocalDate now() {
    return LocalDate.now();
  }

  private static int thisYear() {
    return now().getYear();
  }

  private static int inManyYears() {
    return thisYear() + 30;
  }

  private static int thisMonth() {
    return now().getMonthValue();
  }

  private static int thisWeek() {
    Calendar calendar = Calendar.getInstance();
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  private static int futureQuarter(TimeYear year) {
    return isCurrentYear(year) ? now().getMonthValue() / 3 * 3 : firstPossibleQuarter();
  }

  private static int futureQuarter(TimeWeek week) {
    return isCurrentWeek(week) ? now().getMonthValue() / 3 * 3 : week.firstQuarter();
  }

  private static int futureMonth(TimeQuarter quarter) {
    return isCurrentQuarter(quarter) ? now().getMonthValue() : quarter.firstMonth();
  }

  private static int futureMonth(TimeWeek week) {
    return isCurrentWeek(week) ? now().getMonthValue() : week.firstMonth();
  }

  private static int thatDay(TimeMonth month, int dayOfMonth) {
    int safeDayOfMonth = dayOfMonth == 1 ? dayOfMonth : dayOfMonth - 1; // TODO : Fix this hack
    return month.getYear().atMonth(month).atDay(safeDayOfMonth).getDayOfYear();
  }

  private static int futureDay(TimeMonth month) {
    return isCurrentMonth(month) ? now().getDayOfMonth() : month.atFirstDay().getDayOfMonth();
  }

  private static int futureDay(TimeWeek week) {
    return isCurrentWeek(week) ? now().getDayOfMonth() : week.atFirstDay().getDayOfMonth() - 1;
  }

  private static boolean isCurrentYear(TimeYear year) {
    return year.toYear().getValue() == thisYear();
  }

  private static boolean isCurrentQuarter(TimeQuarter quarter) {
    return isCurrentYear(quarter.getYear()) && quarter.toQuarterOfYear() == (thisMonth() - 1) * 3;
  }

  private static boolean isCurrentMonth(TimeMonth month) {
    return isCurrentQuarter(month.getQuarter()) && isCurrentMonth(month.toMonth().getValue());
  }

  private static boolean isCurrentMonth(int month) {
    return month == thisMonth();
  }

  private static boolean isCurrentWeek(TimeWeek week) {
    return isCurrentYear(week.getYear()) && week.toWeekOfYear() == thisWeek();
  }
}
