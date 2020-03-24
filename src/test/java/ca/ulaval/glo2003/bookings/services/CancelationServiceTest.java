package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.CancelationRefundCalculator;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.mappers.CancelationMapper;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CancelationServiceTest {

  private static CancelationService cancelationService;
  private static CancelationRefundCalculator cancelationRefundCalculator =
      mock(CancelationRefundCalculator.class);
  private static CancelationMapper cancelationMapper = mock(CancelationMapper.class);
  private static TransactionService transactionService = mock(TransactionService.class);

  private Booking booking = aBooking().build();
  private String bedOwner = "bedOwner";

  @BeforeAll
  public static void setUpService() {
    cancelationService =
        new CancelationService(cancelationRefundCalculator, cancelationMapper, transactionService);
  }

  @Test
  public void cancel_withCanceledBooking_shouldThrowBookingAlreadyCanceledException() {
    booking.cancel();

    assertThrows(
        BookingAlreadyCanceledException.class, () -> cancelationService.cancel(booking, bedOwner));
  }
}
