package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import static ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.domain.Price;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {

  private TransactionMapper transactionMapper;

  @BeforeEach
  public void setUpMapper() {
    transactionMapper = new TransactionMapper();
  }

  @Test
  public void toResponse_shouldMapReason() {
    TransactionReasons reason = TransactionReasons.STAY_BOOKED;
    String expectedReason = reason.toString();
    Transaction transaction = aTransaction().withTransactionReason(reason).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedReason, transactionResponse.getReason());
  }

  @Test
  public void toResponse_shouldMapFrom() {
    String expectedFrom = createFrom();
    Transaction transaction = aTransaction().withFrom(expectedFrom).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedFrom, transactionResponse.getFrom());
  }

  @Test
  public void toResponse_shouldMapTo() {
    String expectedTo = createTo();
    Transaction transaction = aTransaction().withTo(expectedTo).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTo, transactionResponse.getTo());
  }

  @Test
  public void toResponse_shouldMapTotal() {
    Price total = createTotal();
    double expectedTotal = total.getValue().doubleValue();
    Transaction transaction = aTransaction().withTotal(total).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTotal, transactionResponse.getTotal());
  }

  @Test
  public void toResponse_shouldMapTimestamp() {
    LocalDateTime timestamp = createTimestamp();
    String expectedTimestamp = timestamp.toString();
    Transaction transaction = aTransaction().withTimestamp(timestamp).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTimestamp, transactionResponse.getTimestamp());
  }
}
