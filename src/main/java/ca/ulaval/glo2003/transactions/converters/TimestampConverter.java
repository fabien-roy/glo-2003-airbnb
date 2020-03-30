package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Timestamp;

public class TimestampConverter {

  public String toString(Timestamp timestamp) {
    return timestamp.getValue().toString();
  }
}
