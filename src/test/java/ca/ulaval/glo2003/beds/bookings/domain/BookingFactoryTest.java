package ca.ulaval.glo2003.beds.bookings.domain;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingFactoryTest {

  BookingFactory bookingFactory;

  @BeforeEach
  public void setUpFactory() {
    bookingFactory = new BookingFactory();
  }

  @Test
  public void create_shouldSetBookingNumber() {
    Booking booking = aBooking().build();

    booking = bookingFactory.create(booking);

    assertNotNull(booking.getNumber());
  }

  @Test
  public void create_shouldSetDifferentBookingNumbers() {
    Booking booking = aBooking().build();
    Booking otherBooking = aBooking().build();

    booking = bookingFactory.create(booking);
    otherBooking = bookingFactory.create(otherBooking);

    assertNotEquals(booking.getNumber(), otherBooking.getNumber());
  }
}
