package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
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
  public void getTransaction_shouldReturnTransactionResponseWithSameParameters() {

    int expectedTotal = 10;
    String expectedTo = "transactionrecipent";
    String expectedFrom = "transactionsender";
    LocalDateTime expectedTimestamp = LocalDateTime.now();
    TransactionReasons expectedReason = TransactionReasons.STAYBOOKED;

    Transaction transactionToMap =
        new Transaction(expectedReason, expectedTo, expectedFrom, expectedTotal, expectedTimestamp);
    TransactionResponse response = transactionMapper.toResponse(transactionToMap);

    assertEquals(response.getTimestamp(), expectedTimestamp);
    assertEquals(response.getTransactionReasons(), expectedReason);
    assertEquals(response.getFrom(), expectedFrom);
    assertEquals(response.getTo(), expectedTo);
    assertEquals(response.getTotal(), expectedTotal);
  }
}
