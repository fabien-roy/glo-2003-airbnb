package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingPeriod;

public class CohabitationLodgingMode implements LodgingMode {

  // TODO : Test this whole thing

  @Override
  public void validateAvailable(Bed bed, Booking booking) {
    if (booking.getColonySize() == null) throw new MissingColonySizeException();

    if (isAvailable(
        bed, booking.getColonySize(), booking.getArrivalDate(), booking.getNumberOfNights()))
      throw new ExceedingAccommodationCapacityException();
  }

  @Override
  public boolean isAvailable(
      Bed bed, Integer minCapacity, BookingDate arrivalDate, int numberOfNights) {
    BookingPeriod period = arrivalDate.periodToDays(numberOfNights);

    for (BookingDate date : period.getDates()) {
      int remainingCapacity = bed.getRemainingCapacityOnDate(date);
      if (remainingCapacity < minCapacity) return false;
    }

    return true;
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.COHABITATION;
  }
}
