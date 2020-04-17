package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  private static Transaction transaction;
  private static ReservationTimestamp reservationTimestamp = mock(ReservationTimestamp.class);

  @BeforeEach
  public void resetMocks() {
    reset(reservationTimestamp);
  }

  private void setUpRefundTransaction() {
    when(reservationTimestamp.isMaxTime()).thenReturn(true);
    setUpTransaction();
  }

  private void setUpNonRefundTransaction() {
    when(reservationTimestamp.isMaxTime()).thenReturn(false);
    setUpTransaction();
  }

  private void setUpTransaction() {
    transaction = aTransaction().withTimestamp(reservationTimestamp).build();
  }

  @Test
  public void isRefund_withMaxTimeTimestamp_shouldReturnTrue() {
    setUpRefundTransaction();

    assertTrue(transaction.isRefund());
  }

  @Test
  public void isRefund_withNonMaxTimeTimestamp_shouldReturnFalse() {
    setUpNonRefundTransaction();

    assertFalse(transaction.isRefund());
  }
}
