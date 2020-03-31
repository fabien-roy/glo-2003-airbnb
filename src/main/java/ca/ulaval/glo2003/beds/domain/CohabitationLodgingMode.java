package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.Booking;

public class CohabitationLodgingMode implements LodgingMode {

  public void validateLodging(Bed bed, Booking booking) {
    // TODO
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.COHABITATION;
  }
}
