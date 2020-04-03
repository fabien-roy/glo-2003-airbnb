package ca.ulaval.glo2003.bookings.converters;

import static ca.ulaval.glo2003.bookings.rest.helpers.CancelationResponseObjectMother.createRefundAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CancelationConverterTest {
  private static CancelationConverter cancelationConverter;
  private static PriceConverter priceConverter = mock(PriceConverter.class);

  private static Price refundAmount = mock(Price.class);
  private static double refundAmountValue = createRefundAmount();

  @BeforeEach
  public void setUpMapper() {
    cancelationConverter = new CancelationConverter(priceConverter);
  }

  @BeforeEach
  public void setUpMock() {
    when(priceConverter.toDouble(refundAmount)).thenReturn(refundAmountValue);
  }

  @Test
  public void toResponse_shouldMapRefundAmount() {
    CancelationResponse cancelationResponse = cancelationConverter.toResponse(refundAmount);

    assertEquals(refundAmountValue, cancelationResponse.getRefundAmount());
  }
}
