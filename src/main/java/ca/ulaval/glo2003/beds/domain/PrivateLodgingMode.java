package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

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
  public Price applyDiscount(Price total, Booking booking, Bed bed) {
    if (booking.getNumberOfNights() < 3) return total;
    else if (booking.getNumberOfNights() < 10) return total.multiply(BigDecimal.valueOf(0.95));
    else if (booking.getNumberOfNights() < 30) return total.multiply(BigDecimal.valueOf(0.9));
    return total.multiply(BigDecimal.valueOf(0.75));
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.PRIVATE;
  }
}
