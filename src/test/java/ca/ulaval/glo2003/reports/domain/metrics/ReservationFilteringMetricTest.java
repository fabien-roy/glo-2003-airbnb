package ca.ulaval.glo2003.reports.domain.metrics;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import java.util.Arrays;
import java.util.Collections;

public abstract class ReservationFilteringMetricTest extends ReportMetricTest {

  protected static Transaction reservationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_BOOKED).build();
  protected static Transaction otherReservationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_BOOKED).build();
  protected static Transaction cancelationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_CANCELED).build();
  protected static Transaction completionTransaction =
      aTransaction().withReason(TransactionReasons.STAY_COMPLETED).build();

  protected void setUpDataWithoutReservation() {
    setUpData(Arrays.asList(cancelationTransaction, completionTransaction));
  }

  protected void setUpDataWithSingleReservation() {
    setUpData(Collections.singletonList(reservationTransaction));
  }

  protected void setUpDataWithMultipleReservations() {
    setUpData(Arrays.asList(reservationTransaction, otherReservationTransaction));
  }
}
