package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingTest {

  private static Booking booking;
  private static Booking otherBooking;
  private static BookingDate arrivalDate;
  private static BookingDate otherArrivalDate;
  private static int numberOfNights;

  @BeforeEach
  public void setUpBooking() {
    numberOfNights = 1;
    arrivalDate = createArrivalDate();
    otherArrivalDate = arrivalDate.plusDays(numberOfNights);

    booking = buildBooking();
    otherBooking = buildOtherBooking();
  }

  private Booking buildBooking() {
    return aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
  }

  private Booking buildOtherBooking() {
    return aBooking().withArrivalDate(otherArrivalDate).withNumberOfNights(numberOfNights).build();
  }

  @Test
  public void cancel_shouldSetStatusToCanceled() {
    booking.cancel();

    assertTrue(booking.isCanceled());
  }

  @Test
  public void getPeriod_shouldReturnPeriodWithArrivalAsStart() {
    BookingPeriod period = booking.getPeriod();

    assertEquals(arrivalDate, period.getStart());
  }

  @Test
  public void getPeriod_shouldReturnPeriodWithDepartureAsEnd() {
    BookingDate departure = arrivalDate.plusDays(numberOfNights - 1);

    BookingPeriod period = booking.getPeriod();

    assertEquals(departure, period.getEnd());
  }

  @Test
  public void isOverlapping_withOverlappingBooking_shouldReturnTrue() {
    boolean result = booking.isOverlapping(booking);

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withNonOverlappingBooking_shouldReturnFalse() {
    boolean result = booking.isOverlapping(otherBooking);

    assertFalse(result);
  }

  @Test
  public void isOverlapping_withOverlappingPeriod_shouldReturnTrue() {
    boolean result = booking.isOverlapping(arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withNonOverlappingPeriod_shouldReturnFalse() {
    boolean result = booking.isOverlapping(otherArrivalDate, numberOfNights);

    assertFalse(result);
  }
}
