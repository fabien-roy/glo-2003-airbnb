package ca.ulaval.glo2003.beds.bookings.transactions.domain;

public enum TransactionReasons {
  STAY_BOOKED("STAY_BOOKED"),
  STAY_COMPLETED("STAY_COMPLETED");

  private String reason;

  TransactionReasons(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return reason;
  }
}
