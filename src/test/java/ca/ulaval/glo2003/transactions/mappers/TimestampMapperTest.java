package ca.ulaval.glo2003.transactions.mappers;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TimestampMapperTest {

  private static TimestampMapper timestampMapper;

  private static Timestamp timestamp = aTimestamp().build();

  @BeforeAll
  public static void setUpMapper() {
    timestampMapper = new TimestampMapper();
  }

  @Test
  public void toString_shouldMapTimestamp() {
    String expectedString = timestamp.getValue().format(DateTimeFormatter.ISO_DATE_TIME) + "Z";

    String actualString = timestampMapper.toString(timestamp);

    assertEquals(expectedString, actualString);
  }
}
