package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;

public class PrivateLodgingMode implements LodgingMode {

  @Override
  public void validateAvailable(Bed bed, Booking booking) {
    if (!isAvailable(
        bed, booking.getColonySize(), booking.getArrivalDate(), booking.getNumberOfNights()))
      throw new BedAlreadyBookedException();
  }

  @Override
  public boolean isAvailable(
      Bed bed, Integer minCapacity, TimeDate arrivalDate, int numberOfNights) {
    return bed.getBookings().stream()
        .noneMatch(booking -> booking.isOverlapping(arrivalDate, numberOfNights));
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.PRIVATE;
  }

  @Override
  public Price applyDiscount(Price total, Bed bed, Booking booking) {
    return total;
  }
}
