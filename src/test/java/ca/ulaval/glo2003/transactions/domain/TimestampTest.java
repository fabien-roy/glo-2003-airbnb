package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.Timestamp.ZONE_OFFSET;
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
  public void construct_withLocalDate_shouldReturnTimestampAtZuluZoneOffset() {
    timestamp = new Timestamp(date);

    assertEquals(date, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void construct_withLocalDate_shouldReturnTimestampAtMax() {
    LocalDateTime max =
        timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDate().atTime(LocalTime.MAX);

    timestamp = new Timestamp(date);

    assertEquals(max, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDateTime());
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDateTime.now().toLocalDate();

    timestamp = Timestamp.now();

    assertEquals(now, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDate());
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
    Timestamp otherTimestamp = new Timestamp(timestamp.getValue());

    boolean result = timestamp.equals(otherTimestamp);

    assertTrue(result);
  }
}
