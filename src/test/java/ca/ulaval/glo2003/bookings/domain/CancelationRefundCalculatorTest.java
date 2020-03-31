package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

public class CancelationRefundCalculatorTest {

    private static CancelationRefundCalculator cancelationRefundCalculator;

    @BeforeAll
    public static void setUpService() {
        cancelationRefundCalculator = new CancelationRefundCalculator();
    }

    @Test
    public void calculateOwnerRefund_shouldReturnHalfOfTotal() {
        Price total = mock(Price.class);
        when(total.getValue()).thenReturn(BigDecimal.valueOf(20.00));
        assertEquals(BigDecimal.valueOf(10.00),cancelationRefundCalculator.calculateOwnerRefund(total));
    }

    @Test
    public void calculateOwnerRefund_shouldReturnHigherHalfOfTotal() {
        Price total = mock(Price.class);
        when(total.getValue()).thenReturn(BigDecimal.valueOf(40.11));
        assertEquals(BigDecimal.valueOf(20.06),cancelationRefundCalculator.calculateOwnerRefund(total));
    }

    @Test
    public void calculateTenantRefund_shouldReturnHalfOfTotal() {
        Price total = mock(Price.class);
        when(total.getValue()).thenReturn(BigDecimal.valueOf(20.00));
        assertEquals(BigDecimal.valueOf(10.00),cancelationRefundCalculator.calculateOwnerRefund(total));
    }

    @Test
    public void calculateTenantRefund_shouldReturnLowerHalfOfTotal() {
        Price total = mock(Price.class);
        when(total.getValue()).thenReturn(BigDecimal.valueOf(40.11));
        assertEquals(BigDecimal.valueOf(20.05),cancelationRefundCalculator.calculateOwnerRefund(total));
    }
}