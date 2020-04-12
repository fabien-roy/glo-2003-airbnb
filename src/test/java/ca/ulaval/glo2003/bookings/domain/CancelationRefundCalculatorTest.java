package ca.ulaval.glo2003.bookings.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CancelationRefundCalculatorTest {

  private static CancelationRefundCalculator cancelationRefundCalculator;
  private static Price evenTotal = new Price(20.00);
  private static Price halfEvenTotal = new Price(10.00);
  private static Price oddTotal = new Price(44.11);
  private static Price higherHalfOddTotal = new Price(22.06);
  private static Price lowerHalfOddTotal = new Price(22.05);

  @BeforeAll
  public static void setUpCalculator() {
    cancelationRefundCalculator = new CancelationRefundCalculator();
  }

  @Test
  public void calculateOwnerRefund_shouldReturnHalfOfTotal() {
    Price ownerRefund = cancelationRefundCalculator.calculateOwnerRefund(evenTotal);

    assertEquals(halfEvenTotal, ownerRefund);
  }

  @Test
  public void calculateOwnerRefund_withOddTotal_shouldReturnHigherHalfOfTotal() {
    Price ownerRefund = cancelationRefundCalculator.calculateOwnerRefund(oddTotal);

    assertEquals(higherHalfOddTotal, ownerRefund);
  }

  @Test
  public void calculateTenantRefund_shouldReturnHalfOfTotal() {
    Price tenantRefund = cancelationRefundCalculator.calculateTenantRefund(evenTotal);

    assertEquals(halfEvenTotal, tenantRefund);
  }

  @Test
  public void calculateTenantRefund_withOddTotal_shouldReturnLowerHalfOfTotal() {
    Price tenantRefund = cancelationRefundCalculator.calculateTenantRefund(oddTotal);

    assertEquals(lowerHalfOddTotal, tenantRefund);
  }
}
