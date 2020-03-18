package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  public void book_withBookings_shouldAddBooking() {
    Packages bookingPackage = createPackageName();
    Booking presentBooking = aBooking().withPackage(bookingPackage).build();
    Bed bed =
        aBed()
            .withBookings(Collections.singletonList(presentBooking))
            .withPricesPerNights(Collections.singletonMap(bookingPackage, createPricePerNight()))
            .build();
    Booking expectedBooking = aBooking().withPackage(bookingPackage).build();

    bed.book(expectedBooking);
    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(presentBooking));
    assertTrue(bookings.contains(expectedBooking));
  }

  @Test
  public void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    PublicKey ownerPublicKey = createOwnerPublicKey();
    Bed bed = aBed().withOwnerPublicKey(ownerPublicKey).build();
    Packages bookingPackage = bed.getPricesPerNight().keySet().iterator().next();
    Booking booking =
        aBooking().withTenantPublicKey(ownerPublicKey).withPackage(bookingPackage).build();

    assertThrows(BookingNotAllowedException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withOverlappingDates_shouldThrowBedAlreadyBookedException() {
    Packages bookingPackage = createPackageName();
    Booking presentBooking = mock(Booking.class);
    when(presentBooking.getPackage()).thenReturn(bookingPackage);
    List<Booking> presentBookings = Collections.singletonList(presentBooking);
    Bed bed =
        aBed()
            .withBookings(presentBookings)
            .withPricesPerNights(Collections.singletonMap(bookingPackage, createPricePerNight()))
            .build();
    Booking booking = aBooking().withPackage(bookingPackage).build();
    when(presentBooking.isOverlapping(booking)).thenReturn(true);

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    Packages bookingPackage = Packages.SWEET_TOOTH;
    Booking booking = aBooking().withPackage(bookingPackage).build();
    Map<Packages, Price> pricesPerNight =
        Collections.singletonMap(Packages.BLOODTHIRSTY, createPricePerNight());
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();

    assertThrows(PackageNotAvailableException.class, () -> bed.book(booking));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void getBookingByNumber_withNoBooking_shouldThrowBookingNotFoundException() {
    UUID bookingNumber = mock(UUID.class);
    Bed bed = aBed().withBookings(Collections.emptyList()).build();

    assertThrows(BookingNotFoundException.class, () -> bed.getBookingByNumber(bookingNumber));
  }

  @Test
  public void
      getBookingByNumber_withNonExistentBookingNumber_shouldThrowBookingNotFoundException() {
    Packages bookingPackage = createPackageName();
    UUID existentBookingNumber = mock(UUID.class);
    UUID nonExistentBookingNumber = mock(UUID.class);
    Booking existentBooking = mock(Booking.class);
    when(existentBooking.getNumber()).thenReturn(existentBookingNumber);
    when(existentBooking.getPackage()).thenReturn(bookingPackage);
    Bed bed =
        aBed()
            .withBookings(Collections.singletonList(existentBooking))
            .withPricesPerNights(Collections.singletonMap(bookingPackage, createPricePerNight()))
            .build();

    assertThrows(
        BookingNotFoundException.class, () -> bed.getBookingByNumber(nonExistentBookingNumber));
  }

  @Test
  public void getBookingByNumber_withOneBooking_shouldGetBooking() {
    UUID bookingNumber = mock(UUID.class);
    Bed bed = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(expectedBooking);

    Booking actualBooking = bed.getBookingByNumber(bookingNumber);

    assertSame(expectedBooking, actualBooking);
  }

  @Test
  public void getBookingByNumber_withMultipleBookings_shouldGetBooking() {
    UUID bookingNumber = mock(UUID.class);
    UUID otherBookingNumber = mock(UUID.class);
    Bed bed = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(expectedBooking);
    Bed otherBed = mock(Bed.class);
    Booking otherExpectedBooking = mock(Booking.class);
    when(otherBed.getBookingByNumber(otherBookingNumber)).thenReturn(otherExpectedBooking);

    Booking actualBooking = bed.getBookingByNumber(bookingNumber);

    assertSame(expectedBooking, actualBooking);
  }
}
