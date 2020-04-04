package ca.ulaval.glo2003.transactions.domain;

public enum TransactionReasons {
  STAY_BOOKED("STAY_BOOKED"),
  STAY_COMPLETED("STAY_COMPLETED"),
  STAY_CANCELED("STAY_CANCELED");

  private String reason;

  TransactionReasons(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return reason;
  }
}
