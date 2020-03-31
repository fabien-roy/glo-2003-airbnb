package ca.ulaval.glo2003.bookings.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CancelationRefundCalculatorTest {

  private static CancelationRefundCalculator cancelationRefundCalculator;
  private static Price total;

  @BeforeAll
  public static void setUpService() {
    cancelationRefundCalculator = new CancelationRefundCalculator();
    total = mock(Price.class);
  }

  @Test
  public void calculateOwnerRefund_shouldReturnHalfOfTotal() {
    when(total.getValue()).thenReturn(BigDecimal.valueOf(20.00));
    assertEquals(
        BigDecimal.valueOf(10.00),
        cancelationRefundCalculator.calculateOwnerRefund(total).getValue());
  }

  @Test
  public void calculateOwnerRefund_shouldReturnHigherHalfOfTotal() {
    when(total.getValue()).thenReturn(BigDecimal.valueOf(40.11));
    assertEquals(
        BigDecimal.valueOf(20.06),
        cancelationRefundCalculator.calculateOwnerRefund(total).getValue());
  }

  @Test
  public void calculateTenantRefund_shouldReturnHalfOfTotal() {
    when(total.getValue()).thenReturn(BigDecimal.valueOf(20.00));
    assertEquals(
        BigDecimal.valueOf(10.00),
        cancelationRefundCalculator.calculateTenantRefund(total).getValue());
  }

  @Test
  public void calculateTenantRefund_shouldReturnLowerHalfOfTotal() {
    when(total.getValue()).thenReturn(BigDecimal.valueOf(40.11));
    assertEquals(
        BigDecimal.valueOf(20.05),
        cancelationRefundCalculator.calculateTenantRefund(total).getValue());
  }
}
