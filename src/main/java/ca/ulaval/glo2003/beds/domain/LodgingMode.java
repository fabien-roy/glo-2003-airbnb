package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.transactions.domain.Price;

public interface LodgingMode {

  void validateAvailable(Bed bed, Booking booking);

  boolean isAvailable(
      Bed bed, Integer minCapacity, ReservationDate arrivalDate, int numberOfNights);

  Price applyDiscount(Price total, Bed bed, Booking booking);

  LodgingModes getName();
}
