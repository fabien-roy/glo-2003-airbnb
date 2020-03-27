package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createBookingNumber;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createTenantPublicKey;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import ca.ulaval.glo2003.bookings.domain.Booking;
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
  private static List<Booking> bookings;

  private static Booking booking = mock(Booking.class);
  private static Booking otherBooking = mock(Booking.class);
  private static UUID bookingNumber;
  private static UUID otherBookingNumber;
  private static Packages bookingPackage;
  private static PublicKey tenantPublicKey;

  @BeforeEach
  public void setUpBed() {
    ownerPublicKey = createOwnerPublicKey();
    bedPackage = createPackageName();
    bookings = Collections.emptyList();
    bookingNumber = createBookingNumber();
    otherBookingNumber = createBookingNumber();
    bookingPackage = bedPackage;
    tenantPublicKey = createTenantPublicKey();

    resetBooking();
    resetOtherBooking();
    bed = buildBed();
  }

  private Bed buildBed() {
    return aBed()
        .withOwnerPublicKey(ownerPublicKey)
        .withPricesPerNights(Collections.singletonMap(bedPackage, createPricePerNight()))
        .withBookings(bookings)
        .build();
  }

  private void resetBooking() {
    reset(booking);
    when(booking.getNumber()).thenReturn(bookingNumber);
    when(booking.getTenantPublicKey()).thenReturn(tenantPublicKey);
    when(booking.getPackage()).thenReturn(bookingPackage);
    when(booking.isOverlapping(otherBooking)).thenReturn(false);
  }

  private void resetOtherBooking() {
    reset(otherBooking);
    when(otherBooking.getNumber()).thenReturn(otherBookingNumber);
    when(otherBooking.getPackage()).thenReturn(bookingPackage);
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
  public void book_withBookings_shouldAddBooking() {
    bookings = Arrays.asList(booking, otherBooking);
    bed = buildBed();

    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(booking));
    assertTrue(bookings.contains(otherBooking));
  }

  @Test
  public void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    tenantPublicKey = new PublicKey(ownerPublicKey.getValue());
    resetBooking();

    assertThrows(BookingNotAllowedException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withOverlappingDates_shouldThrowBedAlreadyBookedException() {
    bookings = Collections.singletonList(booking);
    bed = buildBed();
    when(booking.isOverlapping(otherBooking)).thenReturn(true);

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(otherBooking));
    assertFalse(bed.getBookings().contains(otherBooking));
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
