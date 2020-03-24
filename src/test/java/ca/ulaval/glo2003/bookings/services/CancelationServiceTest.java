package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.mappers.CancelationMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CancelationServiceTest {

  private static CancelationService cancelationService;
  private static CancelationMapper cancelationMapper = mock(CancelationMapper.class);

  private Booking booking = aBooking().build();

  @BeforeAll
  public static void setUpService() {
    cancelationService =
        new CancelationService(cancelationRefundCalculator, cancelationMapper, transactionService);
  }

  @Test
  public void cancel_withCanceledBooking_shouldThrowBookingAlreadyCanceledException() {
    booking.cancel();

    assertThrows(BookingAlreadyCanceledException.class, () -> cancelationService.cancel(booking));
  }
}
