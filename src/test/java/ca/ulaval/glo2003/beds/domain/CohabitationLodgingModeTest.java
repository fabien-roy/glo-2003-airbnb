package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CohabitationLodgingModeTest {

  private static LodgingMode cohabitationLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking = mock(Booking.class);
  private static Integer minCapacity;
  private static BookingDate arrivalDate;
  private static int numberOfNights;
  private static List<Booking> bookings;

  @BeforeAll
  public static void setUpLodgingMode() {
    cohabitationLodgingMode = new CohabitationLodgingMode();
  }

  @BeforeEach
  public void setUpMocks() {
    minCapacity = createCapacity();
    arrivalDate = createArrivalDate();
    numberOfNights = createNumberOfNights();
    bookings = Collections.emptyList();

    resetMocks();
  }

  private void resetMocks() {
    resetBooking();
    resetBed();
  }

  private void resetBed() {
    reset(bed);
    when(bed.getRemainingCapacityOnDate(any())).thenReturn(minCapacity);
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
    when(bed.getRemainingCapacityOnDate(any())).thenReturn(minCapacity - 1);

    boolean result =
        cohabitationLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
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
}
