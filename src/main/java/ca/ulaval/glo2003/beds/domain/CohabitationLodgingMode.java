package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.ExceedingResidualCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CohabitationLodgingMode implements LodgingMode {

  @Override
  public void validateAvailable(Bed bed, Booking booking) {
    if (booking.getColonySize() == null) throw new MissingColonySizeException();

    if (!isAvailable(
        bed, booking.getColonySize(), booking.getArrivalDate(), booking.getNumberOfNights()))
      throw new ExceedingResidualCapacityException();
  }

  @Override
  public boolean isAvailable(
      Bed bed, Integer minCapacity, ReservationDate arrivalDate, int numberOfNights) {
    ReservationPeriod period = arrivalDate.periodToDays(numberOfNights - 1);

    for (ReservationDate date : period.getDates()) {
      int remainingCapacity = bed.getResidualCapacityOnDate(date);
      if (remainingCapacity < minCapacity) return false;
    }

    return true;
  }

  @Override
  public Price applyDiscount(Price total, Bed bed, Booking booking) {
    if (booking.getColonySize() == null) throw new MissingColonySizeException();

    BigDecimal colonySize = BigDecimal.valueOf(booking.getColonySize());
    BigDecimal capacity = BigDecimal.valueOf(bed.getCapacity());
    BigDecimal factor = colonySize.divide(capacity, 4, RoundingMode.HALF_EVEN);

    return total.multiply(factor);
  }

  @Override
  public LodgingModes getName() {
    return LodgingModes.COHABITATION;
  }
}
