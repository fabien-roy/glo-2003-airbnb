package ca.ulaval.glo2003.interfaces.domain;

import static ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp.LOCAL_TIME;
import static ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp.ZONE_OFFSET;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReservationTimestampTest {

  private static ReservationTimestamp reservationTimestamp;

  private static LocalDateTime dateTime = LocalDateTime.now();
  private static LocalDate date = LocalDate.now();
  private static Instant instant = dateTime.toInstant(ZONE_OFFSET);
  private static Instant otherInstant = dateTime.plusDays(1).toInstant(ZONE_OFFSET);

  @BeforeAll
  public static void setUpTimestamp() {
    reservationTimestamp = new ReservationTimestamp(instant);
  }

  @Test
  public void construct_withLocalDate_shouldReturnTimestampAtUTCZoneOffset() {
    reservationTimestamp = new ReservationTimestamp(date);

    assertEquals(date, reservationTimestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void construct_withLocalDate_shouldReturnTimestampAtMax() {
    LocalDateTime max =
        reservationTimestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate().atTime(LocalTime.MAX);

    reservationTimestamp = new ReservationTimestamp(date);

    assertEquals(max, reservationTimestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDateTime());
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDateTime.now().toLocalDate();

    reservationTimestamp = ReservationTimestamp.now();

    assertEquals(now, reservationTimestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void toString_shouldReturnValueToString() {
    assertEquals(reservationTimestamp.toInstant().toString(), reservationTimestamp.toString());
  }

  @Test
  public void toLocalDate_shouldReturnLocalDateAtUTCZoneOffset() {
    LocalDate dateAtUTCZoneOffset = instant.atOffset(ZONE_OFFSET).toLocalDate();

    assertEquals(dateAtUTCZoneOffset, reservationTimestamp.toLocalDate());
  }

  @Test
  public void isMaxTime_withMaxTimeValue_shouldReturnTrue() {
    instant = date.atTime(LOCAL_TIME).toInstant(ZONE_OFFSET);
    reservationTimestamp = new ReservationTimestamp(instant);

    boolean result = reservationTimestamp.isMaxTime();

    assertTrue(result);
  }

  @Test
  public void isMaxTime_withNonMaxTimeValue_shouldReturnFalse() {
    instant = date.atTime(LocalTime.NOON).toInstant(ZONE_OFFSET);
    reservationTimestamp = new ReservationTimestamp(instant);

    boolean result = reservationTimestamp.isMaxTime();

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotTimestamp() {
    Object object = new Object();

    boolean result = reservationTimestamp.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    ReservationTimestamp otherReservationTimestamp = new ReservationTimestamp(otherInstant);

    boolean result = reservationTimestamp.equals(otherReservationTimestamp);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    ReservationTimestamp otherReservationTimestamp =
        new ReservationTimestamp(reservationTimestamp.toInstant());

    boolean result = reservationTimestamp.equals(otherReservationTimestamp);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldValueHashCode() {
    int valueHashCode = reservationTimestamp.toInstant().hashCode();

    int hashCode = reservationTimestamp.hashCode();

    assertEquals(valueHashCode, hashCode);
  }
}
