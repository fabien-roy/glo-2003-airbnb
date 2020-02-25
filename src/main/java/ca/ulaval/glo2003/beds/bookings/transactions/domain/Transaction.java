package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

  private Date timestamp;
  private String from;
  private String to;
  private BigDecimal total;
  private TransactionReasons reason;
}
