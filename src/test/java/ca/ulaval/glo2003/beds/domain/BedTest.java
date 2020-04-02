package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedTest {

  private static Bed bed;
  private static PublicKey ownerPublicKey;
  private static Packages bedPackage;
  private static int capacity;
  private static List<Booking> bookings;
  private static LodgingMode lodgingMode = mock(LodgingMode.class);

  private static Booking booking = mock(Booking.class);
  private static Booking otherBooking = mock(Booking.class);
  private static UUID bookingNumber;
  private static UUID otherBookingNumber;
  private static Packages bookingPackage;
  private static PublicKey tenantPublicKey;
  private static BookingDate arrivalDate;
  private static Integer colonySize;
  private static int numberOfNights;

  @BeforeEach
  public void setUpBed() {
    ownerPublicKey = createOwnerPublicKey();
    bedPackage = createPackageName();
    capacity = createCapacity();
    bookings = Collections.emptyList();
    bookingNumber = createBookingNumber();
    otherBookingNumber = createBookingNumber();
    bookingPackage = bedPackage;
    tenantPublicKey = createTenantPublicKey();
    arrivalDate = createArrivalDate();
    colonySize = capacity;
    numberOfNights = createNumberOfNights();

    resetBooking();
    resetOtherBooking();
    resetLodgingMode();
    bed = buildBed();
  }

  private Bed buildBed() {
    return aBed()
        .withOwnerPublicKey(ownerPublicKey)
        .withPricesPerNights(Collections.singletonMap(bedPackage, createPricePerNight()))
        .withCapacity(capacity)
        .withBookings(bookings)
        .withLodgingMode(lodgingMode)
        .build();
  }

  private void resetBooking() {
    reset(booking);
    when(booking.getNumber()).thenReturn(bookingNumber);
    when(booking.getTenantPublicKey()).thenReturn(tenantPublicKey);
    when(booking.getPackage()).thenReturn(bookingPackage);
    when(booking.getColonySize()).thenReturn(colonySize);
    when(booking.getArrivalDate()).thenReturn(arrivalDate);
    when(booking.getNumberOfNights()).thenReturn(numberOfNights);
    when(booking.isOverlapping(otherBooking)).thenReturn(false);
    when(booking.isOverlapping(arrivalDate, 0)).thenReturn(true);
    when(booking.getColonySize()).thenReturn(colonySize);
  }

  private void resetOtherBooking() {
    reset(otherBooking);
    when(otherBooking.getNumber()).thenReturn(otherBookingNumber);
    when(otherBooking.getPackage()).thenReturn(bookingPackage);
    when(otherBooking.isOverlapping(arrivalDate, 0)).thenReturn(false);
  }

  private void resetLodgingMode() {
    reset(lodgingMode);
    when(lodgingMode.isAvailable(
            any(Bed.class), eq(colonySize), eq(arrivalDate), eq(numberOfNights)))
        .thenReturn(true);
  }

  @Test
  public void getBookings_shouldGetBookings() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();

    List<Booking> bookings = bed.getBookings();

    assertEquals(1, bookings.size());
    assertTrue(bookings.contains(booking));
  }

  @Test
  public void get_withMultipleBookings_shouldGetBooking() {
    bookings = Arrays.asList(booking, otherBooking);
    bed = buildBed();

    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(booking));
    assertTrue(bookings.contains(otherBooking));
  }

  @Test
  public void getRemainingCapacity_withoutBooking_shouldReturnCapacity() {
    int expectedCapacity = bed.getCapacity();

    int remainingCapacity = bed.getRemainingCapacityOnDate(arrivalDate);

    assertEquals(expectedCapacity, remainingCapacity);
  }

  @Test
  public void getRemainingCapacity_withOneBooking_shouldReturnCapacityMinusColonySize() {
    int expectedCapacity = bed.getCapacity() - booking.getColonySize();
    bookings = Collections.singletonList(booking);
    bed = buildBed();

    int remainingCapacity = bed.getRemainingCapacityOnDate(arrivalDate);

    assertEquals(expectedCapacity, remainingCapacity);
  }

  @Test
  public void getRemainingCapacity_withMultipleBookings_shouldReturnCapacityMinusColonySizes() {
    int expectedCapacity =
        bed.getCapacity() - booking.getColonySize() - otherBooking.getColonySize();
    when(otherBooking.isOverlapping(arrivalDate, 0)).thenReturn(true);
    bookings = Arrays.asList(booking, otherBooking);
    bed = buildBed();

    int remainingCapacity = bed.getRemainingCapacityOnDate(arrivalDate);

    assertEquals(expectedCapacity, remainingCapacity);
  }

  @Test
  public void
      getRemainingCapacity_withBookingsOnDifferentDates_shouldReturnCapacityMinusColonySizesOnDate() {
    int expectedCapacity = bed.getCapacity() - booking.getColonySize();
    bookings = Arrays.asList(booking, otherBooking);
    bed = buildBed();

    int remainingCapacity = bed.getRemainingCapacityOnDate(arrivalDate);

    assertEquals(expectedCapacity, remainingCapacity);
  }

  @Test
  public void book_shouldAddBooking() {
    bed.book(booking);
    List<Booking> bookings = bed.getBookings();

    assertEquals(1, bookings.size());
    assertTrue(bookings.contains(booking));
  }

  @Test
  public void book_withBookings_shouldAddBooking() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();

    bed.book(otherBooking);
    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(booking));
    assertTrue(bookings.contains(otherBooking));
  }

  @Test
  public void book_withoutColonySize_shouldAddBooking() {
    colonySize = null;
    resetBooking();

    bed.book(booking);
    List<Booking> bookings = bed.getBookings();

    assertEquals(1, bookings.size());
    assertTrue(bookings.contains(booking));
  }

  @Test
  public void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    tenantPublicKey = new PublicKey(ownerPublicKey.getValue());
    resetBooking();

    assertThrows(BookingNotAllowedException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void
      book_withExceedingAccommodationCapacity_shouldThrowExceedingAccommodationCapacityException() {
    colonySize = capacity + 1;
    resetBooking();

    assertThrows(ExceedingAccommodationCapacityException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void isAvailable_withAvailablePeriod_shouldReturnTrue() {
    boolean result = bed.isAvailable(colonySize, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withUnavailablePeriod_shouldReturnFalse() {
    when(lodgingMode.isAvailable(
            any(Bed.class), eq(colonySize), eq(arrivalDate), eq(numberOfNights)))
        .thenReturn(false);

    boolean result = bed.isAvailable(colonySize, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void isAvailable_withExceedingAccommodationCapacity_shouldReturnFalse() {
    colonySize = capacity + 1;
    resetBooking();

    boolean result = bed.isAvailable(colonySize, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void hasOverlappingBookings_withOverlappingDates_returnTrue() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();
    when(booking.isOverlapping(otherBooking)).thenReturn(true);

    boolean result = bed.hasOverlappingBookings(otherBooking);

    assertTrue(result);
  }

  @Test
  public void hasOverlappingBookings_withoutOverlappingDates_shouldReturnFalse() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();
    when(booking.isOverlapping(otherBooking)).thenReturn(false);

    boolean result = bed.hasOverlappingBookings(otherBooking);

    assertFalse(result);
  }

  @Test
  public void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    bookingPackage = Packages.SWEET_TOOTH;
    bedPackage = Packages.BLOODTHIRSTY;
    resetBooking();
    Bed bed = buildBed();

    assertThrows(PackageNotAvailableException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void getBookingByNumber_withNoBooking_shouldThrowBookingNotFoundException() {
    assertThrows(BookingNotFoundException.class, () -> bed.getBookingByNumber(bookingNumber));
  }

  @Test
  public void
      getBookingByNumber_withNonExistentBookingNumber_shouldThrowBookingNotFoundException() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();
    UUID nonExistentBookingNumber = createBedNumber();

    assertThrows(
        BookingNotFoundException.class, () -> bed.getBookingByNumber(nonExistentBookingNumber));
  }

  @Test
  public void getBookingByNumber_withOneBooking_shouldGetBooking() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();

    Booking actualBooking = bed.getBookingByNumber(bookingNumber);

    assertSame(booking, actualBooking);
  }

  @Test
  public void getBookingByNumber_withMultipleBookings_shouldGetBooking() {
    bookings = Arrays.asList(booking, otherBooking);
    bed = buildBed();

    Booking actualBooking = bed.getBookingByNumber(bookingNumber);

    assertSame(booking, actualBooking);
  }
}
