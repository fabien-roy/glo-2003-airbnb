package ca.ulaval.glo2003.transactions.converters;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TimestampConverterTest {

  private static TimestampConverter timestampConverter;

  private static Timestamp timestamp = aTimestamp().build();

  @BeforeAll
  public static void setUpMapper() {
    timestampConverter = new TimestampConverter();
  }

  @Test
  public void toString_shouldMapTimestamp() {
    String expectedString = timestamp.getValue().format(DateTimeFormatter.ISO_DATE_TIME) + "Z";

    String actualString = timestampConverter.toString(timestamp);

    assertEquals(expectedString, actualString);
  }
}
