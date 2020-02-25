package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.domain.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {

  private TransactionMapper transactionMapper;

  @BeforeEach
  public void setUpMapper() {
    transactionMapper = new TransactionMapper();
  }

  // TODO : Separate each assertion in separate tests
  // TODO : Use TransactionBuilder and TransactionObjectMother
  // TODO : Assert : Expected first, observed second
  @Test
  public void getTransaction_shouldReturnTransactionResponseWithSameParameters() {
    LocalDateTime expectedTimestamp = LocalDateTime.now();
    String expectedTo = "transactionrecipent";
    String expectedFrom = "transactionsender";
    Price expectedTotal = new Price(BigDecimal.ZERO);
    TransactionReasons expectedReason = TransactionReasons.STAY_BOOKED;

    Transaction transactionToMap =
        new Transaction(expectedTimestamp, expectedFrom, expectedTo, expectedTotal, expectedReason);
    TransactionResponse response = transactionMapper.toResponse(transactionToMap);

    assertEquals(response.getTimestamp(), expectedTimestamp);
    assertEquals(response.getTransactionReasons(), expectedReason);
    assertEquals(response.getFrom(), expectedFrom);
    assertEquals(response.getTo(), expectedTo);
    assertEquals(response.getTotal(), expectedTotal.getValue().doubleValue());
  }
}
