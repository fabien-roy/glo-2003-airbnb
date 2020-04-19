package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test ReportQuery
public class ReportQuery {

  private List<Transaction> transactions;
  private ReportScope scope;
  private List<ReportMetric> metrics;
  private List<ReportDimension> dimensions;

  public ReportQuery(
      ReportScope scope, List<ReportMetric> metrics, List<ReportDimension> dimensions) {
    this.scope = scope;
    this.metrics = metrics;
    this.dimensions = dimensions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public ReportScope getScope() {
    return scope;
  }

  public List<ReportMetric> getMetrics() {
    return metrics;
  }

  public List<ReportDimension> getDimensions() {
    return dimensions;
  }

  public List<ReportPeriod> execute() {
    List<ReportPeriod> queriedPeriods = new ArrayList<>();

    for (ReportPeriod period : scope.getReportPeriods()) {
      List<Transaction> periodTransactions =
          transactions.stream()
              .filter(transaction -> period.contains(transaction.getTimestamp()))
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
