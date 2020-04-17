package ca.ulaval.glo2003.interfaces.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReservationDateConverterTest {

  private static ReservationDateConverter reservationDateConverter;

  @BeforeAll
  public static void setUpConverter() {
    reservationDateConverter = new ReservationDateConverter();
  }

  @Test
  public void fromString_shouldConvertBookingDate() {
    ReservationDate expectedDate = new ReservationDate(LocalDate.now());

    ReservationDate reservationDate = reservationDateConverter.fromString(expectedDate.toString());

    assertEquals(expectedDate, reservationDate);
  }

  @Test
  public void fromString_withInvalidBookingDate_shouldThrowInvalidArrivalDateException() {
    String invalidArrivalDate = "invalidArrivalDate";

    Assertions.assertThrows(
        InvalidArrivalDateException.class,
        () -> reservationDateConverter.fromString(invalidArrivalDate));
  }

  @Test
  public void fromString_withBookingDateInThePast_shouldThrowArrivalDateInThePastException() {
    String arrivalDateInThePast = LocalDate.now().minusDays(1).toString();

    Assertions.assertThrows(
        ArrivalDateInThePastException.class,
        () -> reservationDateConverter.fromString(arrivalDateInThePast));
  }

  @Test
  public void fromString_withoutBookingDate_shouldThrowInvalidArrivalDateException() {
    String nullArrivalDate = null;

    Assertions.assertThrows(
        InvalidArrivalDateException.class,
        () -> reservationDateConverter.fromString(nullArrivalDate));
  }
}
