package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionFactory {

  public static final String AIRBNB = "airbnb";

  public Transaction createStayBooked(String tenant, BigDecimal total) {
    LocalDate timestamp = LocalDate.now();
    return new Transaction(timestamp, tenant, AIRBNB, total, TransactionReasons.STAY_BOOKED);
  }

  public Transaction createStayCompleted(String owner, BigDecimal total, int numberOfNights) {
    LocalDate timestamp = LocalDate.now().plusDays(numberOfNights);
    return new Transaction(timestamp, AIRBNB, owner, total, TransactionReasons.STAY_COMPLETED);
  }
}
