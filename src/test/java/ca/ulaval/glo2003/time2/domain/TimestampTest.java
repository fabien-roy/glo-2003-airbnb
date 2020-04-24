package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.Timestamp.ZONE_OFFSET;
import static ca.ulaval.glo2003.time2.domain.helpers.TimeDayBuilder.aDay;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TimestampTest {

  private static Timestamp timestamp;

  private static LocalDateTime dateTime = LocalDateTime.now();
  private static LocalDate date = LocalDate.now();
  private static Instant instant = dateTime.toInstant(ZONE_OFFSET);
  private static Instant otherInstant = dateTime.plusDays(1).toInstant(ZONE_OFFSET);

  @BeforeAll
  public static void setUpTimestamp() {
    timestamp = new Timestamp(instant);
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDateTime.now().toLocalDate();

    timestamp = Timestamp.now();

    assertEquals(now, timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void construct_withLocalDate_shouldReturnTimestampAtUTCZoneOffset() {
    timestamp = new Timestamp(date);

    assertEquals(date, timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void construct_withLocalDate_shouldReturnTimestampAtMax() {
    LocalDateTime max =
        timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate().atTime(LocalTime.MAX);

    timestamp = new Timestamp(date);

    assertEquals(max, timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDateTime());
  }

  @Test
  public void toDay_shouldReturnDayAtUTCZoneOffset() {
    TimeDay day = aDay().withZonedDateTime(instant.atZone(ZONE_OFFSET)).build();

    assertEquals(day, timestamp.toDay());
  }

  @Test
  public void toLocalDate_shouldReturnDateAtUTCZoneOffset() {
    LocalDate date = instant.atZone(ZONE_OFFSET).toLocalDate();

    assertEquals(date, timestamp.toLocalDate());
  }

  @Test
  public void toString_shouldReturnValueToString() {
    assertEquals(timestamp.toInstant().toString(), timestamp.toString());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotTimestamp() {
    Object object = new Object();

    boolean result = timestamp.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Timestamp otherTimestamp = new Timestamp(otherInstant);

    boolean result = timestamp.equals(otherTimestamp);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    Timestamp otherTimestamp = new Timestamp(timestamp.toInstant());

    boolean result = timestamp.equals(otherTimestamp);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldValueHashCode() {
    int valueHashCode = timestamp.toInstant().hashCode();

    int hashCode = timestamp.hashCode();

    assertEquals(valueHashCode, hashCode);
  }
}
