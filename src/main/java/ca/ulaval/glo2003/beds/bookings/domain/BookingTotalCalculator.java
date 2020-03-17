package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class BookingTotalCalculator {

  public Price calculateTotal(Bed bed, Booking booking) {
    Price pricePerNight = bed.getPricePerNight(booking.getPackage());
    Price total = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));
    return applyDiscount(total, booking.getNumberOfNights());
  }

  private Price applyDiscount(Price total, int numberOfNights) {
    if (numberOfNights < 3) return total;
    else if (numberOfNights < 10) return total.multiply(BigDecimal.valueOf(1.05));
    else if (numberOfNights < 30) return total.multiply(BigDecimal.valueOf(1.1));
    return total.multiply(BigDecimal.valueOf(1.25));
  }
}
