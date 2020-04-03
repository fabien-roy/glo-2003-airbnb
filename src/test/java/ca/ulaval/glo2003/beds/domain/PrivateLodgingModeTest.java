package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrivateLodgingModeTest {

  private static LodgingMode privateLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking = mock(Booking.class);
  private static Integer minCapacity;
  private static BookingDate arrivalDate;
  private static int numberOfNights;
  private static List<Booking> bookings;

  @BeforeAll
  public static void setUpLodgingMode() {
    privateLodgingMode = new PrivateLodgingMode();
  }

  @BeforeEach
  public void setUpMocks() {
    minCapacity = createCapacity();
    arrivalDate = createArrivalDate();
    numberOfNights = createNumberOfNights();
    bookings = Collections.emptyList();

    resetMocks();
  }

  private void setUpWithBookings() {
    bookings = Collections.singletonList(booking);
    resetMocks();
  }

  private void resetMocks() {
    resetBooking();
    resetBed();
  }

  private void resetBed() {
    reset(bed);
    when(bed.getBookings()).thenReturn(bookings);
  }

  private void resetBooking() {
    reset(booking);
    when(booking.getColonySize()).thenReturn(minCapacity);
    when(booking.getArrivalDate()).thenReturn(arrivalDate);
    when(booking.getNumberOfNights()).thenReturn(numberOfNights);
    when(booking.isOverlapping(arrivalDate, numberOfNights)).thenReturn(false);
  }

  @Test
  public void isAvailable_withNoBooking_shouldReturnTrue() {
    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withoutOverlappingBooking_shouldReturnTrue() {
    setUpWithBookings();

    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withOverlappingBooking_shouldReturnFalse() {
    setUpWithBookings();
    when(booking.isOverlapping(arrivalDate, numberOfNights)).thenReturn(true);

    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void getName_shouldReturnPrivate() {
    assertEquals(LodgingModes.PRIVATE, privateLodgingMode.getName());
  }

  @ParameterizedTest
  @MethodSource("provideConditionsForApplyDiscount")
  public void applyDiscount_shouldReturnCorrectTotal(
      Price pricePerNight, int numberOfNights, Price expectedTotal) {
    Packages packageName = createPackageName();
    Map<Packages, Price> pricesPerNight = Collections.singletonMap(packageName, pricePerNight);
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();
    Booking booking =
        aBooking().withPackage(packageName).withNumberOfNights(numberOfNights).build();

    Price prix = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));
    Price totalprix = privateLodgingMode.applyDiscount(prix, booking, bed);
    assertEquals(expectedTotal, totalprix);
  }

  private static Stream<Arguments> provideConditionsForApplyDiscount() {
    return Stream.of(
        Arguments.of(
            new Price(BigDecimal.valueOf(100)), 1, new Price(BigDecimal.valueOf(100))), // 100 * 1
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            3,
            new Price(BigDecimal.valueOf(285))), // 100 * 3 * 0.95
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            10,
            new Price(BigDecimal.valueOf(900))), // 100 * 10 * 0.9
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            30,
            new Price(BigDecimal.valueOf(2250)))); // 100 * 30 * 0.75
  }
}
