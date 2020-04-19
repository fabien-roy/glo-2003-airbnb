package ca.ulaval.glo2003.reports.domain.metrics;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReservationsMetricTest extends ReportMetricTest {

  private static Transaction reservationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_BOOKED).build();
  private static Transaction otherReservationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_BOOKED).build();
  private static Transaction cancelationTransaction =
      aTransaction().withReason(TransactionReasons.STAY_CANCELED).build();
  private static Transaction completionTransaction =
      aTransaction().withReason(TransactionReasons.STAY_COMPLETED).build();

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.RESERVATIONS;
  }

  @BeforeAll
  public static void setUpMetric() {
    metric = new ReservationsMetric();
  }

  private void setUpDataWithoutReservation() {
    setUpData(Arrays.asList(cancelationTransaction, completionTransaction));
  }

  private void setUpDataWithSingleReservation() {
    setUpData(Collections.singletonList(reservationTransaction));
  }

  private void setUpDataWithMultipleReservations() {
    setUpData(Arrays.asList(reservationTransaction, otherReservationTransaction));
  }

  @Test
  public void calculate_withoutReservation_shouldCalculateZero() {
    setUpDataWithoutReservation();

    metric.calculate(data);

    assertEquals(0, data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withSingleReservation_shouldCalculateOne() {
    setUpDataWithSingleReservation();

    metric.calculate(data);

    assertEquals(1, data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withMultipleReservations_shouldCalculateNumberOfReservations() {
    setUpDataWithMultipleReservations();

    metric.calculate(data);

    assertEquals(2, data.getMetrics().get(0).getValue());
  }
}
