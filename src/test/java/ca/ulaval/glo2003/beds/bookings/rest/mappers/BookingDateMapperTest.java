package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BookingDateMapperTest {

  @Test
  public void getInvalidDate_shouldReturnTrue() {
    String invalidDate = "This is not a date. No really";

    boolean isInvalid = BookingDateMapper.isNotAValidDate(invalidDate);

    assertTrue(isInvalid);
  }

  @Test
  public void getValidDate_shouldReturnFalse() {
    String validDate = "2020-02-12";

    boolean isInvalid = BookingDateMapper.isNotAValidDate(validDate);

    assertFalse(isInvalid);
  }

  @Test
  public void getFutureDate_shouldReturnFalse() {
    String futureDate = "4034-12-12";

    boolean isBefore = BookingDateMapper.isBeforeToday(futureDate);

    assertFalse(isBefore);
  }

  @Test
  public void getPastDate_shouldReturnTrue() {
    String pastDate = "2007-12-12";

    boolean isBefore = BookingDateMapper.isBeforeToday(pastDate);

    assertTrue(isBefore);
  }
}
