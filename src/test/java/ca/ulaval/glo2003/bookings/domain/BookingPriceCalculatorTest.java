package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.LodgingMode;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BookingPriceCalculatorTest {

  private static BookingPriceCalculator bookingPriceCalculator;

  private static Price total = new Price(100);
  private static Price pricePerNight = mock(Price.class);
  private static Bed bed = mock(Bed.class);
  private static Packages bedPackage = createPackageName();
  private static LodgingMode lodgingMode = mock(LodgingMode.class);
  private static Booking booking = mock(Booking.class);

  @BeforeAll
  public static void setUpCalculator() {
    bookingPriceCalculator = new BookingPriceCalculator();
  }

  public void resetMocks(int numberOfNights) {
    when(booking.getPackage()).thenReturn(bedPackage);
    when(bed.getPricePerNight(bedPackage)).thenReturn(pricePerNight);
    when(booking.getNumberOfNights()).thenReturn(numberOfNights);
    when(pricePerNight.multiply(BigDecimal.valueOf(numberOfNights))).thenReturn(total);
    when(bed.getLodgingMode()).thenReturn(lodgingMode);
    when(lodgingMode.applyDiscount(total, bed, booking)).thenReturn(total);
  }

  @ParameterizedTest
  @MethodSource("provideConditionsToCalculateTotal")
  public void calculateTotal_shouldApplyCorrectDiscount(int numberOfNights, Price total) {
    resetMocks(numberOfNights);

    Price actualTotal = bookingPriceCalculator.calculatePrice(bed, booking);

    assertEquals(total, actualTotal);
  }

  private static Stream<Arguments> provideConditionsToCalculateTotal() {
    return Stream.of(
        Arguments.of(1, new Price(100)),
        Arguments.of(3, new Price(95)),
        Arguments.of(10, new Price(90)),
        Arguments.of(30, new Price(75)));
  }
}
