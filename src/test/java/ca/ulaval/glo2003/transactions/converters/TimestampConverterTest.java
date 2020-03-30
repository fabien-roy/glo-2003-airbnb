package ca.ulaval.glo2003.transactions.converters;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TimestampConverterTest {

  private static TimestampConverter timestampConverter;

  private static Timestamp timestamp = aTimestamp().build();

  @BeforeAll
  public static void setUpConverter() {
    timestampConverter = new TimestampConverter();
  }

  @Test
  public void toString_shouldConvertTimestamp() {
    String expectedString = timestamp.getValue().toString();

    String actualString = timestampConverter.toString(timestamp);

    assertEquals(expectedString, actualString);
  }
}
