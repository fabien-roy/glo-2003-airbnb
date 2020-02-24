package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotAllowedException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  void book_withBookings_shouldAddBooking() {
    Booking presentBooking = aBooking().build();
    Booking expectedBooking = aBooking().build();
    Bed bed = aBed().withBookings(Collections.singletonList(presentBooking)).build();

    bed.book(expectedBooking);
    List<Booking> bookings = bed.getBookings();

    assertEquals(2, bookings.size());
    assertTrue(bookings.contains(presentBooking));
    assertTrue(bookings.contains(expectedBooking));
  }

  @Test
  void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    String ownerPublicKey = createOwnerPublicKey();
    Booking booking = aBooking().withTenantPublicKey(ownerPublicKey).build();
    Bed bed = aBed().withOwnerPublicKey(ownerPublicKey).build();

    assertThrows(BookingNotAllowedException.class, () -> bed.book(booking));
  }

  @Test
  void book_withAlreadyBookedDate_shouldThrowAlreadyBookedException() {
    // TODO
  }

  @Test
  void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    // TODO
  }
}
