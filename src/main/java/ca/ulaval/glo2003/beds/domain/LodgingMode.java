package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.Booking;

public interface LodgingMode {

  void validateLodging(Bed bed, Booking booking);

  LodgingModes getName();
}
