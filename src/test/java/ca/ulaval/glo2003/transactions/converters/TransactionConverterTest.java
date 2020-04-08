package ca.ulaval.glo2003.transactions.converters;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Timestamp;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionConverterTest {

  private static TransactionConverter transactionConverter;

  private static Timestamp timestamp = aTimestamp().build();
  private static Price total = createTotal();
  private static TransactionReasons reason = createReason();
  private static String from = createFrom();
  private static String to = createTo();
  private static Transaction transaction =
      aTransaction()
          .withTimestamp(timestamp)
          .withTotal(total)
          .withReason(reason)
          .withFrom(from)
          .withTo(to)
          .build();

  @BeforeEach
  public void setUpMapper() {
    transactionConverter = new TransactionConverter();
  }

  @Test
  public void toResponse_shouldMapTimestamp() {
    TransactionResponse transactionResponse = transactionConverter.toResponse(transaction);

    assertEquals(timestamp.toString(), transactionResponse.getTimestamp());
  }

  @Test
  public void toResponse_shouldMapTotal() {
    TransactionResponse transactionResponse = transactionConverter.toResponse(transaction);

    assertEquals(total.toDouble(), transactionResponse.getTotal());
  }

  @Test
  public void toResponse_shouldMapReason() {
    TransactionResponse transactionResponse = transactionConverter.toResponse(transaction);

    assertEquals(reason.toString(), transactionResponse.getReason());
  }

  @Test
  public void toResponse_shouldMapFrom() {
    TransactionResponse transactionResponse = transactionConverter.toResponse(transaction);

    assertEquals(from, transactionResponse.getFrom());
  }

  @Test
  public void toResponse_shouldMapTo() {
    TransactionResponse transactionResponse = transactionConverter.toResponse(transaction);

    assertEquals(to, transactionResponse.getTo());
  }
}
