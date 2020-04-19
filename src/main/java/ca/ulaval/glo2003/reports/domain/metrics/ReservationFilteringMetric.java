package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReservationFilteringMetric<T> extends ReportMetric<T> {

  protected List<Transaction> filterReservations(ReportPeriodData data) {
    return data.getTransactions().stream().filter(this::isReservation).collect(Collectors.toList());
  }

  protected boolean isReservation(Transaction transaction) {
    return transaction.getReason().equals(TransactionReasons.STAY_BOOKED);
  }
}
