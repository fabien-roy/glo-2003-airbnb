package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import java.math.BigDecimal;

public class BookingTotalCalculator {

  public BigDecimal calculateTotal(Bed bed, Booking booking) {
    BigDecimal pricePerNight = getPricePerNight(bed, booking.getPackageName());
    BigDecimal total = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));
    return applyDiscount(total, booking.getNumberOfNights());
  }

  // TODO : If Bed.getPackages() is an EnumMap, this will be simplified
  private BigDecimal getPricePerNight(Bed bed, PackageNames packageName) {
    Package bedPackage =
        bed.getPackages().stream()
            .filter(possiblePackage -> possiblePackage.getName().equals(packageName))
            .findFirst()
            .get();
    return bedPackage.getPricePerNight();
  }

  private BigDecimal applyDiscount(BigDecimal total, int numberOfNights) {
    if (numberOfNights < 3) return total;
    else if (numberOfNights < 10) return total.multiply(BigDecimal.valueOf(1.05));
    else if (numberOfNights < 30) return total.multiply(BigDecimal.valueOf(1.1));
    return total.multiply(BigDecimal.valueOf(1.25));
  }
}
