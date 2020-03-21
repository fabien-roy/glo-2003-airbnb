package ca.ulaval.glo2003.beds.bookings.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.createBookingStatus;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingTest {

  @Test
  public void getDepartureDate_shouldReturnArrivalDatePlusNumberOfNights() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    LocalDate expectedDepartureDate = arrivalDate.getValue().plusDays(numberOfNights - 1);
    Booking booking =
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();

    LocalDate departureDate = booking.getDepartureDate();

    assertEquals(expectedDepartureDate, departureDate);
  }

  @Test
  public void isOverlapping_withArrivalDateAfterOtherDepartureDate_shouldReturnFalse() {
    BookingDate arrivalDate = createArrivalDate();
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().minusDays(3));
    int otherNumberOfNights = 2;
    Booking booking = BookingBuilder.aBooking().withArrivalDate(arrivalDate).build();
    Booking otherBooking =
        BookingBuilder.aBooking()
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
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();
    Booking otherBooking = BookingBuilder.aBooking().withArrivalDate(otherArrivalDate).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertFalse(isOverlapping);
  }

  @Test
  public void isOverlapping_withOtherDepartureDateDuringBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().minusDays(1));
    Booking booking =
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();
    Booking otherBooking =
        BookingBuilder.aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }

  @Test
  public void isOverlapping_withOtherArrivalDateDuringBooking_shouldReturnTrue() {
    BookingDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    BookingDate otherArrivalDate = new BookingDate(arrivalDate.getValue().plusDays(1));
    Booking booking =
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();
    Booking otherBooking =
        BookingBuilder.aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();

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
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();
    Booking otherBooking =
        BookingBuilder.aBooking()
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
        BookingBuilder.aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .build();
    Booking otherBooking =
        BookingBuilder.aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertTrue(isOverlapping);
  }

  @Test
  public void construct_shouldSetStatusToBooked() {
    BookingStatuses expectedBookingStatus = BookingStatuses.BOOKED;

    Booking booking = BookingBuilder.aBooking()
            .build();

    BookingStatuses BookingStatus = booking.getStatus();

    assertEquals(expectedBookingStatus, BookingStatus);

  }
}
