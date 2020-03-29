package ca.ulaval.glo2003.transactions.rest.helpers;

import static ca.ulaval.glo2003.transactions.rest.helpers.TransactionResponseObjectMother.*;

import ca.ulaval.glo2003.transactions.rest.TransactionResponse;

public class TransactionResponseBuilder {

  private TransactionResponseBuilder() {}

  private String DEFAULT_TIMESTAMP = createTimestamp();
  private String timestamp = DEFAULT_TIMESTAMP;

  private String DEFAULT_FROM = createFrom();
  private String from = DEFAULT_FROM;

  private String DEFAULT_TO = createTo();
  private String to = DEFAULT_TO;

  private double DEFAULT_TOTAL = createTotal();
  private double total = DEFAULT_TOTAL;

  private String DEFAULT_REASON = createReason();
  private String reason = DEFAULT_REASON;

  public static TransactionResponseBuilder aTransactionResponse() {
    return new TransactionResponseBuilder();
  }

  public TransactionResponse build() {
    return new TransactionResponse(timestamp, from, to, total, reason);
  }
}
