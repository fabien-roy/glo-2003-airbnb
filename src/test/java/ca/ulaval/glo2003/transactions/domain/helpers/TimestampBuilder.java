package ca.ulaval.glo2003.transactions.domain.helpers;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampObjectMother.createValue;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.LocalDateTime;

public class TimestampBuilder {

  private TimestampBuilder() {}

  private LocalDateTime DEFAULT_VALUE = createValue();
  private LocalDateTime value = DEFAULT_VALUE;

  public static TimestampBuilder aTimestamp() {
    return new TimestampBuilder();
  }

  public Timestamp build() {
    return new Timestamp(value);
  }
}
