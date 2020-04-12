package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.UUID;

public class BookingFactory {

  public Booking create(Booking booking, Price total) {
    booking.setNumber(createNumber());
    booking.setTotal(total);
    booking.setStatus(BookingStatuses.BOOKED);
    return booking;
  }

  private BookingNumber createNumber() {
    return new BookingNumber(UUID.randomUUID());
  }
}
