package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriodDataBuilder {

  private List<ReportEvent> DEFAULT_EVENTS = new ArrayList<>();
  private List<ReportEvent> events = DEFAULT_EVENTS;

  private List<ReportMetricData> DEFAULT_METRICS = new ArrayList<>();
  private List<ReportMetricData> metrics = DEFAULT_METRICS;

  private List<ReportDimensionData> DEFAULT_DIMENSIONS = new ArrayList<>();
  private List<ReportDimensionData> dimensions = DEFAULT_DIMENSIONS;

  private Price DEFAULT_INCOMES = createPrice();
  private Price incomes = DEFAULT_INCOMES;

  public static ReportPeriodDataBuilder aReportPeriodData() {
    return new ReportPeriodDataBuilder();
  }

  public ReportPeriodDataBuilder withIncomes(Price incomes) {
    this.incomes = incomes;
    return this;
  }

  public ReportPeriodDataBuilder withANumberOfCancelations(int numberOfCancelations) {
    addANumberOfType(numberOfCancelations, ReportEventTypes.CANCELATION);
    return this;
  }

  public ReportPeriodDataBuilder withANumberOfReservations(int numberOfReservations) {
    addANumberOfType(numberOfReservations, ReportEventTypes.RESERVATION);
    return this;
  }

  private void addANumberOfType(int numberOfType, ReportEventTypes type) {
    for (int i = 0; i < numberOfType; i++) {
      ReportEvent event = aReportEvent().withType(type).withIncomes(incomes).build();
      events.add(event);
    }
  }

  public ReportPeriodData build() {
    ReportPeriodData data = new ReportPeriodData(events);
    metrics.forEach(data::addMetric);
    dimensions.forEach(data::addDimension);
    return data;
  }
}
