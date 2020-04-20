package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class BookingPriceCalculator {

  public Price calculatePrice(Bed bed, Booking booking) {
    Price pricePerNight = bed.getPricePerNight(booking.getPackage());
    Price price = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));

    price = bed.getLodgingMode().applyDiscount(price, bed, booking);

    return applyNumberOfNightsDiscount(price, booking.getNumberOfNights());
  }

  public Price applyNumberOfNightsDiscount(Price price, int numberOfNights) {
    if (numberOfNights < 3) return price;
    else if (numberOfNights < 10) return applyDiscount(price, 0.95);
    else if (numberOfNights < 30) return applyDiscount(price, 0.9);
    return applyDiscount(price, 0.75);
  }

  private Price applyDiscount(Price price, double factor) {
    return price.multiply(BigDecimal.valueOf(factor));
  }
}
