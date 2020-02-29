package ca.ulaval.glo2003.beds.bookings.domain;

import java.util.UUID;

public class BookingFactory {

  public Booking create(Booking booking) {
    UUID bookingNumber = UUID.randomUUID();
    booking.setNumber(bookingNumber);
    return booking;
  }
}
