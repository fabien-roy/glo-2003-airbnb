package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test ReservationsReportMetric
public class ReservationsMetric extends ReportMetric<Integer> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.RESERVATIONS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    Integer reservations = filterReservations(data).size();
    data.addMetrics(toMetricData(reservations));
  }

  private List<Transaction> filterReservations(ReportPeriodData data) {
    return data.getTransactions().stream().filter(this::isReservation).collect(Collectors.toList());
  }

  private boolean isReservation(Transaction transaction) {
    return transaction.getReason().equals(TransactionReasons.STAY_BOOKED);
  }
}
