package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeObjectMother.createLodgingModeName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventObjectMother.createEventType;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.time.domain.*;
import ca.ulaval.glo2003.transactions.domain.Price;

public class ReportEventBuilder {

  private ReportEventTypes DEFAULT_TYPE = createEventType();
  private ReportEventTypes type;

  private TimeDate DEFAULT_DATE = aTimeDate().build();
  private TimeDate date = DEFAULT_DATE;

  private LodgingModes DEFAULT_LODGING_MODE = createLodgingModeName();
  private LodgingModes lodgingMode = DEFAULT_LODGING_MODE;

  private Packages DEFAULT_PACKAGE_NAME = createPackageName();
  private Packages packageName = DEFAULT_PACKAGE_NAME;

  private Price DEFAULT_INCOMES = createPrice();
  private Price incomes = DEFAULT_INCOMES;

  public static ReportEventBuilder aReportEvent() {
    return new ReportEventBuilder();
  }

  public ReportEventBuilder withDate(TimeDate date) {
    this.date = date;
    return this;
  }

  public ReportEventBuilder withType(ReportEventTypes type) {
    this.type = type;
    return this;
  }

  public ReportEventBuilder withIncomes(Price incomes) {
    this.incomes = incomes;
    return this;
  }

  public ReportEvent build() {
    return new ReportEvent(type, date, lodgingMode, packageName, incomes);
  }
}
