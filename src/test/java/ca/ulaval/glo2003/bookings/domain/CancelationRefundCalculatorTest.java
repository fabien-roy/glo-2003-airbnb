package ca.ulaval.glo2003.bookings.domain;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CancelationRefundCalculatorTest {

  private static CancelationRefundCalculator cancelationRefundCalculator;
  private static Price totalEven;
  private static Price totalOdd;

  @BeforeAll
  public static void setUpService() {
    cancelationRefundCalculator = new CancelationRefundCalculator();
    totalEven = new Price(BigDecimal.valueOf(20.00));
    totalOdd = new Price(BigDecimal.valueOf(40.11));
  }

  @Test
  public void calculateOwnerRefund_shouldReturnHalfOfTotal() {
    assertEquals(
        BigDecimal.valueOf(10.00),
        cancelationRefundCalculator.calculateOwnerRefund(totalEven));
  }

  @Test
  public void calculateOwnerRefund_shouldReturnHigherHalfOfTotal() {
    assertEquals(
        BigDecimal.valueOf(20.06),
        cancelationRefundCalculator.calculateOwnerRefund(totalOdd));
  }

  @Test
  public void calculateTenantRefund_shouldReturnHalfOfTotal() {
    assertEquals(
        BigDecimal.valueOf(10.00),
        cancelationRefundCalculator.calculateTenantRefund(totalEven));
  }

  @Test
  public void calculateTenantRefund_shouldReturnLowerHalfOfTotal() {
    assertEquals(
        BigDecimal.valueOf(20.05),
        cancelationRefundCalculator.calculateTenantRefund(totalOdd));
  }
}
