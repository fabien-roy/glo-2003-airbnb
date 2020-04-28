package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;

public class ReportEvent {

  public final ReportEventTypes type;
  public final TimeDate date;
  public final LodgingModes lodgingMode;
  public final Packages packageName;
  public final Price incomes;

  public ReportEvent(
      ReportEventTypes type,
      TimeDate date,
      LodgingModes lodgingMode,
      Packages packageName,
      Price incomes) {
    this.date = date;
    this.lodgingMode = lodgingMode;
    this.packageName = packageName;
    this.incomes = incomes;
    this.type = type;
  }

  public ReportEventTypes getType() {
    return type;
  }

  public TimeDate getDate() {
    return date;
  }

  public LodgingModes getLodgingMode() {
    return lodgingMode;
  }

  public Packages getPackage() {
    return packageName;
  }

  public Price getIncomes() {
    return incomes;
  }
}
