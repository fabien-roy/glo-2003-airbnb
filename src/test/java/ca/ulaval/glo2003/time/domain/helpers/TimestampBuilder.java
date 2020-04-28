package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimestampObjectMother.createInstant;

import ca.ulaval.glo2003.time.domain.Timestamp;
import java.time.Instant;

public class TimestampBuilder {

  public static Instant DEFAULT_INSTANT = createInstant();
  public static Instant instant = DEFAULT_INSTANT;

  public static TimestampBuilder aTimestamp() {
    return new TimestampBuilder();
  }

  public Timestamp build() {
    return new Timestamp(instant);
  }
}
