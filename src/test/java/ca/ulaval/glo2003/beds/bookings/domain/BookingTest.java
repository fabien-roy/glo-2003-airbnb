package ca.ulaval.glo2003.beds.bookings.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingTest {

  @Test
  public void getDepartureDate_shouldReturnArrivalDatePlusNumberOfNights() {
    LocalDate arrivalDate = createArrivalDate();
    int numberOfNights = 3;
    LocalDate expectedDepartureDate = arrivalDate.plusDays(numberOfNights - 1);
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfNights).build();

    LocalDate departureDate = booking.getDepartureDate();

    assertEquals(expectedDepartureDate, departureDate);
  }

  @Test
  public void isOverlapping_withArrivalDateAfterOtherDepartureDate_shouldReturnFalse() {
    LocalDate arrivalDate = createArrivalDate();
    LocalDate otherArrivalDate = arrivalDate.minusDays(3);
    int otherNumberOfDays = 2;
    Booking booking = aBooking().withArrivalDate(arrivalDate).build();
    Booking otherBooking =
        aBooking().withArrivalDate(otherArrivalDate).withNumberOfNights(otherNumberOfDays).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertFalse(isOverlapping);
  }

  @Test
  public void isOverlapping_withDepartureDateBeforeOtherArrivalDate_shouldReturnFalse() {
    LocalDate arrivalDate = createArrivalDate();
    LocalDate otherArrivalDate = arrivalDate.plusDays(3);
    int numberOfDays = 2;
    Booking booking =
        aBooking().withArrivalDate(arrivalDate).withNumberOfNights(numberOfDays).build();
    Booking otherBooking = aBooking().withArrivalDate(otherArrivalDate).build();

    boolean isOverlapping = booking.isOverlapping(otherBooking);

    assertFalse(isOverlapping);
  }
}
