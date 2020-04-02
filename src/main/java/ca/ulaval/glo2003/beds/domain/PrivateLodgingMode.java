package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;

public class PrivateLodgingMode implements LodgingMode {

  @Override
  public void validateAvailable(Bed bed, Booking booking) {
    if (!isAvailable(
        bed, booking.getColonySize(), booking.getArrivalDate(), booking.getNumberOfNights()))
      throw new BedAlreadyBookedException();
  }

  @Override
  public boolean isAvailable(
      Bed bed, Integer minCapacity, BookingDate arrivalDate, int numberOfNights) {
    return bed.getBookings().stream()
        .noneMatch(booking -> booking.isOverlapping(arrivalDate, numberOfNights));
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.PRIVATE;
  }
}
