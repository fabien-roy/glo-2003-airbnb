package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.TimeQuarter.firstPossibleQuarter;
import static ca.ulaval.glo2003.time.domain.TimeQuarter.lastPossibleQuarter;

import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimeQuarter;
import ca.ulaval.glo2003.time.domain.TimeYear;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class TimeDateObjectMother {

  private TimeDateObjectMother() {}

  public static Year createYear() {
    return Year.of(Faker.instance().random().nextInt(thisYear(), inManyYears()));
  }

  public static int createQuarterOfYear(TimeYear year) {
    return Faker.instance().random().nextInt(futureQuarter(year), lastPossibleQuarter());
  }

  public static Month createMonth(TimeQuarter quarter) {
    return Month.of(
        Faker.instance().random().nextInt(futureMonth(quarter), quarter.lastMonthOfQuarter()));
  }

  public static int createWeekOfYear(TimeYear year) {
    return 0; // TODO
  }

  public static int createDayOfYear(TimeYear year) {
    return Faker.instance().random().nextInt(futureDay(year), year.atLastDay().getDayOfYear());
  }

  public static int createDayOfYear(TimeMonth month, int dayOfMonth) {
    return Faker.instance()
        .random()
        .nextInt(thatDay(month, dayOfMonth), month.getYear().atLastDay().getDayOfYear());
  }

  public static int createDayOfMonth(TimeMonth month) {
    return Faker.instance().random().nextInt(futureDay(month), month.atLastDay().getDayOfMonth());
  }

  public static int createDayOfMonth(TimeMonth month, int dayOfYear) {
    return month.getYear().toYear().atDay(dayOfYear).getDayOfMonth();
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

  private static int futureQuarter(TimeYear year) {
    return isCurrentYear(year) ? now().getMonthValue() / 3 * 3 : firstPossibleQuarter();
  }

  private static int thisMonth() {
    return now().getMonthValue();
  }

  private static int futureMonth(TimeQuarter quarter) {
    return isCurrentQuarter(quarter) ? now().getMonthValue() : quarter.firstMonthOfQuarter();
  }

  private static int futureDay(TimeYear year) {
    return isCurrentYear(year) ? now().getDayOfYear() : year.atFirstDay().getDayOfYear();
  }

  private static int thatDay(TimeMonth month, int dayOfMonth) {
    return month.getYear().atMonth(month).atDay(dayOfMonth).getDayOfYear();
  }

  private static int futureDay(TimeMonth month) {
    return isCurrentMonth(month) ? now().getDayOfMonth() : month.atFirstDay().getDayOfMonth();
  }

  private static boolean isCurrentYear(TimeYear year) {
    return year.toYear().getValue() == thisYear();
  }

  private static boolean isCurrentQuarter(TimeQuarter quarter) {
    return isCurrentYear(quarter.getYear()) && quarter.getQuarterOfYear() == (thisMonth() - 1) * 3;
  }

  private static boolean isCurrentMonth(TimeMonth month) {
    return isCurrentQuarter(month.getQuarter()) && isCurrentMonth(month.toMonth().getValue());
  }

  private static boolean isCurrentMonth(int month) {
    return month == thisMonth();
  }
}
