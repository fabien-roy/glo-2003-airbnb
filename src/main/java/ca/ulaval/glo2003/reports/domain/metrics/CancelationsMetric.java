package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.time.domain.Timestamp;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO : Test CancelationsReportMetric
public class CancelationsMetric extends ReportMetric<Integer> {

  @Override
  public ReportMetrics getMetricName() {
    return ReportMetrics.CANCELATIONS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    List<Transaction> cancelationTransactions = filterCancelations(data);
    Integer cancelations = calculateUniqueCancelations(cancelationTransactions);
    data.addMetrics(toMetricData(cancelations));
  }

  private List<Transaction> filterCancelations(ReportPeriodData data) {
    return data.getTransactions().stream().filter(this::isCancelation).collect(Collectors.toList());
  }

  private Integer calculateUniqueCancelations(List<Transaction> transactions) {
    Map<Timestamp, Transaction> uniqueCancelations = new HashMap<>();
    transactions.forEach(
        transaction -> uniqueCancelations.putIfAbsent(transaction.getTimestamp(), transaction));
    return uniqueCancelations.values().size();
  }

  public boolean isCancelation(Transaction transaction) {
    return transaction.getReason().equals(TransactionReasons.STAY_CANCELED)
        && !transaction.isRefund();
  }
}
