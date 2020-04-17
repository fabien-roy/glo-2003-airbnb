package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test ReportQuery
public class ReportQuery {

  private List<Transaction> transactions;
  private List<ReportPeriod> periods;
  private List<ReportMetric> metrics;
  private List<ReportDimension> dimensions;

  public ReportQuery(
      List<ReportPeriod> periods, List<ReportMetric> metrics, List<ReportDimension> dimensions) {
    this.periods = periods;
    this.metrics = metrics;
    this.dimensions = dimensions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public List<ReportPeriod> execute() {
    List<ReportPeriod> queriedPeriods = new ArrayList<>();

    for (ReportPeriod period : periods) {
      List<Transaction> periodTransactions =
          transactions.stream()
              .filter(transaction -> period.contains(transaction.getReservationTimestamp()))
              .collect(Collectors.toList());
      period.setSingleData(new ReportPeriodData(periodTransactions));

      List<ReportPeriodData> data = splitDataInDimensions(period.getData());
      calculateMetrics(data);

      period.setData(data);
      queriedPeriods.add(period);
    }

    return queriedPeriods;
  }

  private List<ReportPeriodData> splitDataInDimensions(List<ReportPeriodData> data) {
    List<ReportPeriodData> splitData = new ArrayList<>(data);

    for (ReportDimension dimension : dimensions) {
      splitData = dimension.splitAll(splitData);
    }

    return splitData;
  }

  private void calculateMetrics(List<ReportPeriodData> data) {
    data.forEach(datum -> metrics.forEach(metric -> metric.calculate(datum)));
  }
}
