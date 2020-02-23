package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.util.HashMap;
import java.util.Map;

public enum TransactionReasons {
  STAYBOOKED("StayBooked"),
  STAYCOMPLETED("StayCompleted");

  private String reason;
  private static final Map<String, TransactionReasons> lookup = new HashMap<>();

  static {
    for (TransactionReasons reason : TransactionReasons.values()) {
      lookup.put(reason.toString(), reason);
    }
  }

  TransactionReasons(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return reason;
  }

  public static TransactionReasons get(String reason) {
    TransactionReasons foundTransaction = lookup.get(reason);

    // if (foundTransaction == null) throw new InvalidTransactionReasonException();

    return foundTransaction;
  }
}
