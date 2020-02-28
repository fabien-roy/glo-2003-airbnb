package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import ca.ulaval.glo2003.beds.domain.Price;
import java.time.LocalDateTime;

public class TransactionFactory {

  public static final String AIRBNB = "airbnb";

  public Transaction createStayBooked(String tenant, Price total) {
    LocalDateTime timestamp = LocalDateTime.now();
    return new Transaction(timestamp, tenant, AIRBNB, total, TransactionReasons.STAY_BOOKED);
  }

  public Transaction createStayCompleted(String owner, Price total, int numberOfNights) {
    LocalDateTime timestamp = LocalDateTime.now().plusDays(numberOfNights);
    return new Transaction(timestamp, AIRBNB, owner, total, TransactionReasons.STAY_COMPLETED);
  }
}
