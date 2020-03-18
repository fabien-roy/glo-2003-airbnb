package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.UUID;

public class BookingFactory {

  public Booking create(Booking booking, Price total) {
    UUID bookingNumber = UUID.randomUUID();
    booking.setNumber(bookingNumber);
    booking.setTotal(total);
    return booking;
  }
}
