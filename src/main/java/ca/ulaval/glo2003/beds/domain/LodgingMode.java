package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;

public interface LodgingMode {

  void validateAvailable(Bed bed, Booking booking);

  boolean isAvailable(Bed bed, Integer minCapacity, BookingDate arrivalDate, int numberOfNights);

  LodgingModes getName();
}
