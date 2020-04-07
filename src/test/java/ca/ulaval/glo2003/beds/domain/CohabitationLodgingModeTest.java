package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.ExceedingResidualCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
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
  private static Price total = new Price(BigDecimal.valueOf(100));
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
    when(bed.getResidualCapacityOnDate(any())).thenReturn(minCapacity);
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
    when(bed.getResidualCapacityOnDate(arrivalDate)).thenReturn(minCapacity - 1);

    boolean result =
        cohabitationLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void
      validateAvailable_withoutUnavailableBed_shouldThrowExceedingResidualCapacityException() {
    when(bed.getResidualCapacityOnDate(arrivalDate)).thenReturn(minCapacity - 1);

    assertThrows(
        ExceedingResidualCapacityException.class,
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
  @MethodSource("provideConditionsToApplyDiscount")
  public void applyDiscount_shouldReturnCorrectTotal(
      Integer colonySize, int capacity, Price expectedTotal) {
    when(booking.getColonySize()).thenReturn(colonySize);
    when(bed.getCapacity()).thenReturn(capacity);

    Price discountedTotal = cohabitationLodgingMode.applyDiscount(total, bed, booking);

    assertEquals(expectedTotal, discountedTotal);
  }

  private static Stream<Arguments> provideConditionsToApplyDiscount() {
    return Stream.of(
        Arguments.of(100, 100, new Price(BigDecimal.valueOf(100))),
        Arguments.of(100, 1000, new Price(BigDecimal.valueOf(10))),
        Arguments.of(1000, 100, new Price(BigDecimal.valueOf(1000))),
        Arguments.of(525, 50, new Price(BigDecimal.valueOf(1050))),
        Arguments.of(27, 89, new Price(BigDecimal.valueOf(30.34))));
  }

  public void applyDiscount_withoutColonySize_shouldThrowMissingColonySizeException() {
    when(booking.getColonySize()).thenReturn(null);

    assertThrows(
        MissingColonySizeException.class,
        () -> cohabitationLodgingMode.applyDiscount(total, bed, booking));
  }
}
