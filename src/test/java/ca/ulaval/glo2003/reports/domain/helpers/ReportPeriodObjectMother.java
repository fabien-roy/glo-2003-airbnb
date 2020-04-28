package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;

import java.time.Month;

public class ReportPeriodObjectMother {

  private ReportPeriodObjectMother() {}

  public static String createPeriodName() {
    return randomEnum(Month.class).name();
  }
}
