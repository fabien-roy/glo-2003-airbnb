package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createTotal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.transactions.domain.Price;
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

    booking = bookingFactory.create(booking, mock(Price.class));

    assertNotNull(booking.getNumber());
  }

  @Test
  public void create_shouldSetDifferentBookingNumbers() {
    Booking booking = aBooking().build();
    Booking otherBooking = aBooking().build();

    booking = bookingFactory.create(booking, mock(Price.class));
    otherBooking = bookingFactory.create(otherBooking, mock(Price.class));

    assertNotEquals(booking.getNumber(), otherBooking.getNumber());
  }

  @Test
  public void create_shouldSetTotal() {
    Price expectedTotal = createTotal();
    Booking booking = aBooking().build();

    booking = bookingFactory.create(booking, expectedTotal);

    assertEquals(expectedTotal, booking.getTotal());
  }
}
