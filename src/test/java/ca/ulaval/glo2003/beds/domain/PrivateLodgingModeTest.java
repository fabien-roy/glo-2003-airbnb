package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static ca.ulaval.glo2003.interfaces.domain.helpers.ReservationDateObjectMother.createBookingDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrivateLodgingModeTest {

  private static LodgingMode privateLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking = mock(Booking.class);
  private static Integer minCapacity;
  private static ReservationDate arrivalDate;
  private static int numberOfNights;
  private static List<Booking> bookings;

  @BeforeAll
  public static void setUpLodgingMode() {
    privateLodgingMode = new PrivateLodgingMode();
  }

  @BeforeEach
  public void setUpMocks() {
    minCapacity = createCapacity();
    arrivalDate = createBookingDate();
    numberOfNights = createNumberOfNights();
    bookings = Collections.emptyList();

    resetMocks();
  }

  private void setUpWithBookings() {
    bookings = Collections.singletonList(booking);
    resetMocks();
  }

  private void setUpWithOverlappingBookings() {
    setUpWithBookings();
    when(booking.isOverlapping(arrivalDate, numberOfNights)).thenReturn(true);
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
    setUpWithOverlappingBookings();

    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void validateAvailable_withNonOverlappingBooking_shouldNotThrowException() {
    setUpWithBookings();

    assertDoesNotThrow(() -> privateLodgingMode.validateAvailable(bed, booking));
  }

  @Test
  public void validateAvailable_withOverlappingBooking_shouldThrowBedAlreadyBookedException() {
    setUpWithOverlappingBookings();

    assertThrows(
        BedAlreadyBookedException.class, () -> privateLodgingMode.validateAvailable(bed, booking));
  }

  @Test
  public void getName_shouldReturnPrivate() {
    assertEquals(LodgingModes.PRIVATE, privateLodgingMode.getName());
  }

  @Test
  public void applyDiscount_shouldReturnSameTotal() {
    Price total = new Price(100);

    Price discountedTotal = privateLodgingMode.applyDiscount(total, bed, booking);

    assertEquals(total, discountedTotal);
  }
}
