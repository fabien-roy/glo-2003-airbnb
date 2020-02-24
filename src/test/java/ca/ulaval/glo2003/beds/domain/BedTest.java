package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
  public void book_withAlreadyBookedDate_shouldThrowBedAlreadyBookedException() {
    LocalDate alreadyBookedDate = createArrivalDate();
    Booking presentBooking = aBooking().withArrivalDate(alreadyBookedDate).build();
    List<Booking> presentBookings = Collections.singletonList(presentBooking);
    Booking booking = aBooking().withArrivalDate(alreadyBookedDate).build();
    Bed bed = aBed().withBookings(presentBookings).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void
      book_withPrecedingArrivalDateAndOverlappingNumberOfDays_shouldThrowBedAlreadyBookedException() {
    LocalDate alreadyBookedDate = createArrivalDate();
    LocalDate bookingDate = alreadyBookedDate.minusDays(1);
    int bookingNumberOfDays = 3;
    Booking presentBooking = aBooking().withArrivalDate(alreadyBookedDate).build();
    List<Booking> presentBookings = Collections.singletonList(presentBooking);
    Booking booking =
        aBooking().withArrivalDate(bookingDate).withNumberOfNights(bookingNumberOfDays).build();
    Bed bed = aBed().withBookings(presentBookings).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withArrivalDateWithinNumberOfDays_shouldThrowBedAlreadyBookedException() {
    LocalDate alreadyBookedDate = createArrivalDate();
    LocalDate bookingDate = alreadyBookedDate.plusDays(1);
    int alreadyBookedNumberOfDays = 3;
    Booking presentBooking =
        aBooking()
            .withArrivalDate(alreadyBookedDate)
            .withNumberOfNights(alreadyBookedNumberOfDays)
            .build();
    List<Booking> presentBookings = Collections.singletonList(presentBooking);
    Booking booking = aBooking().withArrivalDate(bookingDate).build();
    Bed bed = aBed().withBookings(presentBookings).build();
    PackageNames bookingPackage = bed.getPackages().get(0).getName();

    assertThrows(BedAlreadyBookedException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }

  @Test
  public void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    Booking booking = aBooking().build();
    PackageNames bookingPackage = PackageNames.SWEET_TOOTH;
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    List<Package> packages = Collections.singletonList(bedPackage);
    Bed bed = aBed().withPackages(packages).build();

    assertThrows(PackageNotAvailableException.class, () -> bed.book(booking, bookingPackage));
    assertFalse(bed.getBookings().contains(booking));
  }
}
