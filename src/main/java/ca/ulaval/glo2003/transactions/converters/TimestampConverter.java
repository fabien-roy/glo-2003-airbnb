package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  public String toString(Timestamp timestamp) {
    return timestamp.getValue().format(DATE_TIME_FORMATTER) + "Z";
  }
}
