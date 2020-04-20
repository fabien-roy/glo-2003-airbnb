package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.reports.domain.ReportEventTypes;

public class ReportEventObjectMother {

  private ReportEventObjectMother() {}

  public static ReportEventTypes createEventType() {
    return randomEnum(ReportEventTypes.class);
  }
}
