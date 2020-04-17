package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.interfaces.domain.helpers.ReservationDateObjectMother.createDate;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingTest {

  private static Booking booking;
  private static Booking otherBooking;
  private static ReservationDate arrivalDate;
  private static ReservationDate otherArrivalDate;
  private static int numberOfNights;

  @BeforeEach
  public void setUpBooking() {
    numberOfNights = 3;
    arrivalDate = createDate();
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
  public void getDepartureDate_shouldReturnArrivalDatePlusNumberOfNights() {
    ReservationDate departureDate = arrivalDate.plusDays(numberOfNights - 1);

    ReservationDate date = booking.getDepartureDate();

    assertEquals(departureDate, date);
  }

  @Test
  public void cancel_shouldSetStatusToCanceled() {
    booking.cancel();

    assertTrue(booking.isCanceled());
  }

  @Test
  public void getPeriod_shouldReturnPeriodWithArrivalAsStart() {
    ReservationPeriod period = booking.getPeriod();

    assertEquals(arrivalDate, period.getStart());
  }

  @Test
  public void getPeriod_shouldReturnPeriodWithDepartureAsEnd() {
    ReservationDate departureDate = arrivalDate.plusDays(numberOfNights - 1);

    ReservationPeriod period = booking.getPeriod();

    assertEquals(departureDate, period.getEnd());
  }

  @Test
  public void getPeriod_withOneNight_shouldReturnPeriodWithArrivalAsStart() {
    numberOfNights = 1;
    booking = buildBooking();

    ReservationPeriod period = booking.getPeriod();

    assertEquals(arrivalDate, period.getStart());
  }

  @Test
  public void getPeriod_withOneNight_shouldReturnPeriodWithArrivalAsEnd() {
    numberOfNights = 1;
    booking = buildBooking();

    ReservationPeriod period = booking.getPeriod();

    assertEquals(arrivalDate, period.getEnd());
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
  public void isOverlapping_withOverlappingBookingDate_shouldReturnTrue() {
    boolean result = booking.isOverlapping(booking.getArrivalDate());

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withNonOverlappingBookingDate_shouldReturnFalse() {
    boolean result = booking.isOverlapping(otherBooking.getArrivalDate());

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
