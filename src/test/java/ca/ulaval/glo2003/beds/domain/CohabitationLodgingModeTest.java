package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createPackageName;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CohabitationLodgingModeTest {

  private static LodgingMode cohabitationLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking = mock(Booking.class);
  private static Integer minCapacity;
  private static BookingDate arrivalDate;
  private static int numberOfNights;

  @BeforeAll
  public static void setUpLodgingMode() {
    cohabitationLodgingMode = new CohabitationLodgingMode();
  }

  @BeforeEach
  public void setUpMocks() {
    minCapacity = createCapacity();
    arrivalDate = createArrivalDate();
    numberOfNights = createNumberOfNights();
    resetMocks();
  }

  private void resetMocks() {
    resetBooking();
    resetBed();
  }

  private void resetBed() {
    reset(bed);
    when(bed.getRemainingCapacityOnDate(any())).thenReturn(minCapacity);
    when(bed.getCapacity()).thenReturn(minCapacity);
  }

  private void resetBooking() {
    reset(booking);
    when(booking.getColonySize()).thenReturn(minCapacity);
    when(booking.getArrivalDate()).thenReturn(arrivalDate);
    when(booking.getNumberOfNights()).thenReturn(numberOfNights);
  }

  @Test
  public void isAvailable_withNoBooking_shouldReturnTrue() {
    boolean result =
        cohabitationLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withRemainingCapacity_shouldReturnTrue() {
    boolean result =
        cohabitationLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withoutRemainingCapacity_shouldReturnFalse() {
    when(bed.getRemainingCapacityOnDate(arrivalDate)).thenReturn(minCapacity - 1);

    boolean result =
        cohabitationLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void
      validateAvailable_withoutUnavailableBed_shouldThrowExceedingAccommodationCapacityException() {
    when(bed.getRemainingCapacityOnDate(arrivalDate)).thenReturn(minCapacity - 1);

    assertThrows(
        ExceedingAccommodationCapacityException.class,
        () -> cohabitationLodgingMode.validateAvailable(bed, booking));
  }

  @Test
  public void validateLodging_withoutColonySize_shouldThrowMissingColonySizeException() {
    when(booking.getColonySize()).thenReturn(null);

    assertThrows(
        MissingColonySizeException.class,
        () -> cohabitationLodgingMode.validateAvailable(bed, booking));
  }

  @Test
  public void getName_shouldReturnCohabitation() {
    assertEquals(LodgingModes.COHABITATION, cohabitationLodgingMode.getName());
  }

  @ParameterizedTest
  @MethodSource("provideConditionsForApplyDiscount")
  public void applyDiscount_shouldReturnCorrectTotal(
      Price pricePerNight,
      int numberOfNights,
      Integer colonySize,
      int bedCapacity,
      Price expectedTotal) {
    Packages packageName = createPackageName();
    Map<Packages, Price> pricesPerNight = Collections.singletonMap(packageName, pricePerNight);
    Bed bed = aBed().withPricesPerNights(pricesPerNight).withCapacity(bedCapacity).build();
    Booking booking =
        aBooking()
            .withPackage(packageName)
            .withNumberOfNights(numberOfNights)
            .withColonySize(colonySize)
            .build();

    Price prix = pricePerNight.multiply(BigDecimal.valueOf(booking.getNumberOfNights()));
    Price totalprix = cohabitationLodgingMode.applyDiscount(prix, bed, booking);
    assertEquals(expectedTotal, totalprix);
  }

  private static Stream<Arguments> provideConditionsForApplyDiscount() {
    return Stream.of(
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            1,
            10,
            20,
            new Price(BigDecimal.valueOf(50))), // 100 * 1
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            3,
            10,
            20,
            new Price(BigDecimal.valueOf(142.5))), // 100 * 3 * 0.95
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            10,
            10,
            20,
            new Price(BigDecimal.valueOf(450))), // 100 * 10 * 0.9
        Arguments.of(
            new Price(BigDecimal.valueOf(100)),
            30,
            10,
            20,
            new Price(BigDecimal.valueOf(1125)))); // 100 * 30 * 0.75
  }
}
