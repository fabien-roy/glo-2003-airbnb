package ca.ulaval.glo2003.transactions.domain;

import ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp;

public class TransactionFactory {

  public Transaction createStayBooked(String from, String to, Price total) {
    return new Transaction(
        ReservationTimestamp.now(), from, to, total, TransactionReasons.STAY_BOOKED);
  }

  public Transaction createStayCompleted(
      String from, String to, Price total, ReservationTimestamp reservationTimestamp) {
    return new Transaction(
        reservationTimestamp, from, to, total, TransactionReasons.STAY_COMPLETED);
  }

  public Transaction createStayCanceled(String from, String to, Price total) {
    return new Transaction(
        ReservationTimestamp.now(), from, to, total, TransactionReasons.STAY_CANCELED);
  }

  public Transaction createStayRefunded(
      String from, String to, Price total, ReservationTimestamp reservationTimestamp) {
    return new Transaction(reservationTimestamp, from, to, total, TransactionReasons.STAY_CANCELED);
  }
}
