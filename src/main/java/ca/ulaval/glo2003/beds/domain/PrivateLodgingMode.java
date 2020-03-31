package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;

public class PrivateLodgingMode implements LodgingMode {

  // TODO : Tests
  public void validateLodging(Bed bed, Booking booking) {
    if (bed.hasOverlappingBookings(booking)) throw new BedAlreadyBookedException();
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.PRIVATE;
  }
}
