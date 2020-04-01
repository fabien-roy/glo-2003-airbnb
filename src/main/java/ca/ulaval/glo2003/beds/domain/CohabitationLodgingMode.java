package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;

public class CohabitationLodgingMode implements LodgingMode {

  @Override
  public void validateAvailable(Bed bed, Booking booking) {
    if (booking.getColonySize() == null) throw new MissingColonySizeException();

    // TODO : Validate lodging using total colony size per day (#164)
  }

  @Override
  public boolean isAvailable(
      Bed bed, Integer minCapacity, BookingDate arrivalDate, int numberOfNights) {
    // TODO
    return false;
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.COHABITATION;
  }
}
