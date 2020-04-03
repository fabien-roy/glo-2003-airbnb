package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class BookingTotalCalculator {

  public Price calculateTotal(Bed bed, Booking booking) {
    Price pricePerNight = bed.getPricePerNight(booking.getPackage());
    Price total = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));

    total = bed.getLodgingMode().applyDiscount(total, bed, booking);

    return applyNumberOfNightsDiscount(total, booking.getNumberOfNights());
  }

  public Price applyNumberOfNightsDiscount(Price total, int numberOfNights) {
    if (numberOfNights < 3) return total;
    else if (numberOfNights < 10) return applyDiscount(total, 0.95);
    else if (numberOfNights < 30) return applyDiscount(total, 0.9);
    return applyDiscount(total, 0.75);
  }

  private Price applyDiscount(Price total, double factor) {
    return total.multiply(BigDecimal.valueOf(factor));
  }
}
