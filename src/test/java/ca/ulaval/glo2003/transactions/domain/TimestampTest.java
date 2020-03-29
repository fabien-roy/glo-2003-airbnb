package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.Timestamp.ZONE_OFFSET;
import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimestampTest {

  private static Timestamp timestamp;

  private static LocalDateTime dateTime = LocalDateTime.now();
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

    assertEquals(now, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void inDays_shouldReturnTimestampInDays(int days) {
    LocalDate inDays = LocalDateTime.now().plusDays(days).toLocalDate();

    timestamp = Timestamp.inDays(days);

    assertEquals(inDays, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void inDays_shouldReturnTimestampAtMidnight() {
    LocalDateTime inDays = LocalDateTime.now().toLocalDate().atTime(LocalTime.MIDNIGHT);

    timestamp = Timestamp.inDays(0);

    assertEquals(inDays, timestamp.getValue().atOffset(ZONE_OFFSET).toLocalDateTime());
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
