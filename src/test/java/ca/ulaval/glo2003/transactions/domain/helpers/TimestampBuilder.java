package ca.ulaval.glo2003.transactions.domain.helpers;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampObjectMother.createValue;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.Instant;

public class TimestampBuilder {

  private TimestampBuilder() {}

  private Instant DEFAULT_VALUE = createValue();
  private Instant value = DEFAULT_VALUE;

  public static TimestampBuilder aTimestamp() {
    return new TimestampBuilder();
  }

  public Timestamp build() {
    return new Timestamp(value);
  }
}
