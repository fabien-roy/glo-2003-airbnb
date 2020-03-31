package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PrivateLodgingModeTest {

  private static PrivateLodgingMode privateLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking = aBooking().build();

  @BeforeAll
  public static void setUpLodgingMode() {
    privateLodgingMode = new PrivateLodgingMode();
  }

  @Test
  public void validateLodging_withOverlappingBookings_shouldThrowBedAlreadyBookedException() {
    when(bed.hasOverlappingBookings(booking)).thenReturn(true);

    assertThrows(
        BedAlreadyBookedException.class, () -> privateLodgingMode.validateLodging(bed, booking));
  }

  @Test
  public void getName_shouldReturnPrivate() {
    assertEquals(LodgingModes.PRIVATE, privateLodgingMode.getName());
  }
}
