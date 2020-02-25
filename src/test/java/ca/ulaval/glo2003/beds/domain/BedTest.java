package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  public void book_withBookings_shouldAddBooking() {
    Booking presentBooking = aBooking().build();
    Booking expectedBooking = aBooking().build();
    Bed bed = aBed().withBookings(Collections.singletonList(presentBooking)).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    bed.book(expectedBooking, bookingPackage);
    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(presentBooking));
    assertTrue(bookings.contains(expectedBooking));
  }

  @Test
  public void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    String ownerPublicKey = createOwnerPublicKey();
    Booking booking = aBooking().withTenantPublicKey(ownerPublicKey).build();
    Bed bed = aBed().withOwnerPublicKey(ownerPublicKey).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    assertThrows(BookingNotAllowedException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withOverlappingDates_shouldThrowBedAlreadyBookedException() {
    Booking booking = aBooking().build();
    Booking presentBooking = mock(Booking.class);
    when(presentBooking.isOverlapping(booking)).thenReturn(true);
    List<Booking> presentBookings = Collections.singletonList(presentBooking);
    Bed bed = aBed().withBookings(presentBookings).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    Booking booking = aBooking().build();
    PackageNames bookingPackage = PackageNames.SWEET_TOOTH;
    Package bedPackage = aPackage().withName(PackageNames.BLOODTHIRSTY).build();
    List<Package> packages = Collections.singletonList(bedPackage);
    Bed bed = aBed().withPackages(packages).build();

    assertThrows(PackageNotAvailableException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }
}
