package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;

public class CohabitationLodgingMode implements LodgingMode {

  public void validateLodging(Bed bed, Booking booking) {
    if (booking.getColonySize() == null) throw new MissingColonySizeException();

    // TODO : Validate lodging using total colony size per day (#164)
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.COHABITATION;
  }
}
