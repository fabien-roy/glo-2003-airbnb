package ca.ulaval.glo2003.transactions.domain;

public class TransactionFactory {

  public Transaction createStayBooked(String from, String to, Price total) {
    return new Transaction(Timestamp.now(), from, to, total, TransactionReasons.STAY_BOOKED);
  }

  public Transaction createStayCompleted(String from, String to, Price total, int numberOfNights) {
    Timestamp timestamp = Timestamp.inDays(numberOfNights);
    return new Transaction(timestamp, from, to, total, TransactionReasons.STAY_COMPLETED);
  }

  public Transaction createStayCanceled(String from, String to, Price total) {
    // TODO
    return null;
  }

  public Transaction createStayRefunded(String from, String to, Price total, int numberOfNights) {
    // TODO
    return null;
  }
}
