package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CohabitationLodgingModeTest {

  private static LodgingMode cohabitationLodgingMode;
  private static Bed bed = aBed().build();
  private static Booking booking = mock(Booking.class);

  @BeforeAll
  public static void setUpLodgingMode() {
    cohabitationLodgingMode = new CohabitationLodgingMode();
  }

  @Test
  public void validateLodging_withoutColonySize_shouldThrowMissingColonySizeException() {
    when(booking.getColonySize()).thenReturn(null);

    assertThrows(
        MissingColonySizeException.class,
        () -> cohabitationLodgingMode.validateLodging(bed, booking));
  }

  @Test
  public void getName_shouldReturnCohabitation() {
    assertEquals(LodgingModes.COHABITATION, cohabitationLodgingMode.getName());
  }
}
