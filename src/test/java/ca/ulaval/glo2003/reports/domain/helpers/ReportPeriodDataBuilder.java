package ca.ulaval.glo2003.reports.domain.helpers;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.transactions.domain.Price;

import java.util.ArrayList;
import java.util.List;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;

public class ReportPeriodDataBuilder {

  private List<ReportEvent> DEFAULT_EVENTS = new ArrayList<>();
  private List<ReportEvent> events = DEFAULT_EVENTS;

  private List<ReportMetricData> DEFAULT_METRICS = new ArrayList<>();
  private List<ReportMetricData> metrics = DEFAULT_METRICS;

  private List<ReportDimensionData> DEFAULT_DIMENSIONS = new ArrayList<>();
  private List<ReportDimensionData> dimensions = DEFAULT_DIMENSIONS;

  public static ReportPeriodDataBuilder aReportPeriodData() {
    return new ReportPeriodDataBuilder();
  }

  public ReportPeriodDataBuilder withANumberOfCancelations(int numberOfCancelations) {
    addANumberOfType(numberOfCancelations, ReportEventTypes.CANCELATION);
    return this;
  }

  public ReportPeriodDataBuilder withANumberOfReservations(int numberOfReservations) {
    addANumberOfType(numberOfReservations, ReportEventTypes.RESERVATION);
    return this;
  }

  private ReportPeriodDataBuilder addANumberOfType(int numberOfType, ReportEventTypes type) {
    for (int i = 0; i < numberOfType; i++) {
      ReportEvent event = aReportEvent().withType(type).build();
      events.add(event);
    }
    return this;
  }

  public ReportPeriodDataBuilder withIncomes(Price incomes) {
    events.forEach(event -> event = aReportEvent().withType(event.getType()).withDate(event.getDate()).withIncomes(incomes).build());
    return this;
  }

  public ReportPeriodData build() {
    ReportPeriodData data = new ReportPeriodData(events);
    metrics.forEach(data::addMetric);
    dimensions.forEach(data::addDimension);
    return data;
  }
}
