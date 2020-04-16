package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createBookingNumber;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingNumberTest {

  private static BookingNumber bookingNumber;
  private static BookingNumber otherBookingNumber;

  @BeforeEach
  public void resetBookingNumbers() {
    bookingNumber = createBookingNumber();
    otherBookingNumber = createBookingNumber();
  }

  @Test
  public void toUUID_shouldReturnValueAsUUID() {
    UUID value = UUID.randomUUID();
    bookingNumber = new BookingNumber(value);

    UUID actualValue = bookingNumber.toUUID();

    assertEquals(value, actualValue);
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingNumber() {
    Object object = new Object();

    boolean result = bookingNumber.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    boolean result = bookingNumber.equals(otherBookingNumber);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    otherBookingNumber = new BookingNumber(bookingNumber.toUUID());

    boolean result = bookingNumber.equals(otherBookingNumber);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldReturnValueHashCode() {
    int hashCode = bookingNumber.hashCode();

    assertEquals(bookingNumber.toUUID().hashCode(), hashCode);
  }
}
