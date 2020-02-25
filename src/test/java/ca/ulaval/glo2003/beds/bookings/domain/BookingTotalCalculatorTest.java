package ca.ulaval.glo2003.beds.bookings.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.domain.Price;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BookingTotalCalculatorTest {

  @ParameterizedTest
  @MethodSource("provideConditionsForCalculateTotal")
  public void calculateTotal_shouldReturnCorrectTotal(
      Price pricePerNight, int numberOfNights, Price expectedTotal) {
    PackageNames packageName = createPackageName();
    Map<PackageNames, Price> pricesPerNight = Collections.singletonMap(packageName, pricePerNight);
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();
    Booking booking =
        aBooking().withPackage(packageName).withNumberOfNights(numberOfNights).build();
    BookingTotalCalculator bookingTotalCalculator = new BookingTotalCalculator();

    Price total = bookingTotalCalculator.calculateTotal(bed, booking);

    assertEquals(expectedTotal, total);
  }

  private static Stream<Arguments> provideConditionsForCalculateTotal() {
    return Stream.of(
        Arguments.of(
            new Price(BigDecimal.valueOf(100)), 1, new Price(BigDecimal.valueOf(100))), // 100 * 1
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            3,
            new Price(BigDecimal.valueOf(315))), // 100 * 3 * 0.95
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            10,
            new Price(BigDecimal.valueOf(1100))), // 100 * 10 * 0.9
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            30,
            new Price(BigDecimal.valueOf(3750)))); // 100 * 30 * 0.75
  }
}
