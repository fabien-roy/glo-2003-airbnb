package ca.ulaval.glo2003.beds.bookings.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BookingTotalCalculatorTest {

  @ParameterizedTest
  @MethodSource("provideConditionsForCalculateTotal")
  public void calculateTotal_shouldReturnCorrectTotal(
      BigDecimal pricePerNight, int numberOfNights, BigDecimal expectedTotal) {
    PackageNames packageName = createPackageName();
    Package bedPackage = aPackage().withName(packageName).withPricePerNight(pricePerNight).build();
    List<Package> packages = Collections.singletonList(bedPackage);
    Bed bed = aBed().withPackages(packages).build();
    Booking booking =
        aBooking().withPackage(packageName).withNumberOfNights(numberOfNights).build();
    BookingTotalCalculator bookingTotalCalculator = new BookingTotalCalculator();

    BigDecimal total = bookingTotalCalculator.calculateTotal(bed, booking);

    assertEquals(expectedTotal, total);
  }

  private static Stream<Arguments> provideConditionsForCalculateTotal() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(100), 1, BigDecimal.valueOf(100)), // 100 * 1
        Arguments.of(BigDecimal.valueOf(100), 3, BigDecimal.valueOf(315)), // 100 * 3 * 0.95
        Arguments.of(BigDecimal.valueOf(100), 10, BigDecimal.valueOf(1100)), // 100 * 10 * 0.9
        Arguments.of(BigDecimal.valueOf(100), 30, BigDecimal.valueOf(3750))); // 100 * 30 * 0.75
  }
}
