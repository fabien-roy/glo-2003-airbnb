package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  private static Transaction transaction;
  private static Timestamp timestamp = mock(Timestamp.class);

  @BeforeEach
  public void resetMocks() {
    reset(timestamp);
  }

  private void setUpRefundTransaction() {
    when(timestamp.isMaxTime()).thenReturn(true);
    setUpTransaction();
  }

  private void setUpNonRefundTransaction() {
    when(timestamp.isMaxTime()).thenReturn(false);
    setUpTransaction();
  }

  private void setUpTransaction() {
    transaction = aTransaction().withTimestamp(timestamp).build();
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
