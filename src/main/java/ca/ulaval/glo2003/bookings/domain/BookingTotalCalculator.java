package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class BookingTotalCalculator {

  public Price calculateTotal(Bed bed, Booking booking) {
    Price pricePerNight = bed.getPricePerNight(booking.getPackage());
    Price total = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));

    return bed.getLodgingMode().applyDiscount(total, booking, bed);
  }
}
