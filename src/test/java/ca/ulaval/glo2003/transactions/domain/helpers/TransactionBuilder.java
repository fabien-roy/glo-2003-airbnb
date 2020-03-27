package ca.ulaval.glo2003.transactions.domain.helpers;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Timestamp;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;

public class TransactionBuilder {

  private TransactionBuilder() {}

  private Timestamp DEFAULT_TIMESTAMP = aTimestamp().build();
  private Timestamp timestamp = DEFAULT_TIMESTAMP;

  private String DEFAULT_FROM = createFrom();
  private String from = DEFAULT_FROM;

  private String DEFAULT_TO = createTo();
  private String to = DEFAULT_TO;

  private Price DEFAULT_TOTAL = createTotal();
  private Price total = DEFAULT_TOTAL;

  private TransactionReasons DEFAULT_REASON = createReason();
  private TransactionReasons reason = DEFAULT_REASON;

  public static TransactionBuilder aTransaction() {
    return new TransactionBuilder();
  }

  public TransactionBuilder withReason(TransactionReasons reason) {
    this.reason = reason;
    return this;
  }

  public TransactionBuilder withFrom(String from) {
    this.from = from;
    return this;
  }

  public TransactionBuilder withTo(String to) {
    this.to = to;
    return this;
  }

  public TransactionBuilder withTotal(Price total) {
    this.total = total;
    return this;
  }

  public TransactionBuilder withTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public Transaction build() {
    return new Transaction(timestamp, from, to, total, reason);
  }
}
