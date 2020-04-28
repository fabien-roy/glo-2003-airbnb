package ca.ulaval.glo2003.bookings.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CancelationConverterTest {

  private static CancelationConverter cancelationConverter;

  private static Price refundAmount = Price.valueOf(100);

  @BeforeEach
  public void setUpMapper() {
    cancelationConverter = new CancelationConverter();
  }

  @Test
  public void toResponse_shouldMapRefundAmount() {
    CancelationResponse cancelationResponse = cancelationConverter.toResponse(refundAmount);

    assertEquals(refundAmount.toDouble(), cancelationResponse.getRefundAmount());
  }
}
