package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriodData {

  private List<Transaction> transactions; // TODO : ReportPeriodData should not have transactions
  private List<ReportMetricData> metrics = new ArrayList<>();
  private List<ReportDimensionData> dimensions = new ArrayList<>();

  public ReportPeriodData(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void addMetrics(ReportMetricData metric) {
    metrics.add(metric);
  }

  public void addDimension(ReportDimensionData dimension) {
    dimensions.add(dimension);
  }
}
