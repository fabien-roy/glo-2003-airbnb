package ca.ulaval.glo2003.transactions.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimestampTest {

  private static Timestamp timestamp;

  private static LocalDateTime date = LocalDateTime.now();
  private static LocalDateTime otherDate = date.plusDays(1);

  @BeforeAll
  public static void setUpTimestamp() {
    timestamp = new Timestamp(date);
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDateTime now = LocalDateTime.now();

    timestamp = Timestamp.now();

    assertEquals(now, timestamp.getValue());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void inDays_shouldReturnTimestampInDays(int days) {
    LocalDate inDays = LocalDateTime.now().plusDays(days).toLocalDate();

    timestamp = Timestamp.inDays(days);

    assertEquals(inDays, timestamp.getValue().toLocalDate());
  }

  @Test
  public void inDays_shouldReturnTimestampAtMidnight() {
    LocalDateTime inDays = LocalDateTime.now().toLocalDate().atTime(LocalTime.MIDNIGHT);

    timestamp = Timestamp.inDays(0);

    assertEquals(inDays, timestamp.getValue());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotTimestamp() {
    Object object = new Object();

    boolean result = timestamp.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Timestamp otherTimestamp = new Timestamp(otherDate);

    boolean result = timestamp.equals(otherTimestamp);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    Timestamp otherTimestamp = new Timestamp(date);

    boolean result = timestamp.equals(otherTimestamp);

    assertTrue(result);
  }
}
