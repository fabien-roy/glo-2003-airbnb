package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingTest {

  @Test
  public void construct_shouldSetStatusToBooked() {
    Booking booking = aBooking().build();

    assertFalse(booking.isCanceled());
  }

  @Test
  public void cancel_shouldSetStatusToCanceled() {
    Booking booking = aBooking().build();

    booking.cancel();

    assertTrue(booking.isCanceled());
  }

  @Test
  public void getDepartureDate_shouldReturnArrivalDatePlusNumberOfNights() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    LocalDate expectedDepartureDate = arrivalDate.getValue().plusDays(numberOfNights - 1);
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();

    LocalDate departureDate = booking.getDepartureDate();

    assertEquals(expectedDepartureDate, departureDate);
  }

  @Test
  public void isOverlapping_withArrivalDateAfterOtherDepartureDate_shouldReturnFalse() {
    BookingDate arrivalDate = createArrivalDate();
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().minusDays(3));
    int otherNumberOfNights = 2;
    Booking booking = aBooking().withArrivalDate(arrivalDate).build();
    Booking otherBooking =
        aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertFalse(isOverlapping);
  }

  @Test
  public void isOverlapping_withDepartureDateBeforeOtherArrivalDate_shouldReturnFalse() {
    BookingDate arrivalDate = createArrivalDate();
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().plusDays(3));
    int numberOfNights = 2;
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
    Booking otherBooking = aBooking().withArrivalDate(otherArrivalDate).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertFalse(isOverlapping);
  }

  @Test
  public void isOverlapping_withOtherDepartureDateDuringBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().minusDays(1));
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking().withArrivalDate(otherArrivalDate).withNumberOfNights(numberOfNights).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }

  @Test
  public void isOverlapping_withOtherArrivalDateDuringBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().plusDays(1));
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking().withArrivalDate(otherArrivalDate).withNumberOfNights(numberOfNights).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }

  @Test
  public void isOverlapping_withOtherBookingDuringBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 4;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().plusDays(1));
    int otherNumberOfNights = 2;
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }

  @Test
  public void isOverlapping_withBookingDuringOtherBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 4;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().minusDays(1));
    int otherNumberOfNights = 6;
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }
}
