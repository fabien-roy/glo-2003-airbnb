package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import static ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.domain.Price;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    BigDecimal value = BigDecimal.valueOf(100);
    Price expectedTotal = new Price(value);
    String expectedTo = "transactionrecipent";
    String expectedFrom = "transactionsender";
    LocalDate expectedTimestamp = LocalDate.now();
    TransactionReasons expectedReason = TransactionReasons.STAY_BOOKED;

    Transaction transactionToMap =
        new Transaction(expectedTimestamp, expectedFrom, expectedTo, expectedTotal, expectedReason);
    TransactionResponse response = transactionMapper.toResponse(transactionToMap);

    assertEquals(response.getTimestamp(), expectedTimestamp);
    assertEquals(response.getTransactionReasons(), expectedReason);
    assertEquals(response.getFrom(), expectedFrom);
    assertEquals(response.getTo(), expectedTo);
    assertEquals(response.getTotal(), expectedTotal);
  }

  @Test
  public void toResponse_shouldMapTransactionReason() {
    TransactionReasons expectedTransactionReason = TransactionReasons.STAY_BOOKED;
    Transaction transaction =
        aTransaction().withTransactionReason(expectedTransactionReason).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTransactionReason, transactionResponse.getTransactionReasons());
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
    Price expectedTotal = createTotal();
    Transaction transaction = aTransaction().withTotal(expectedTotal).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTotal, transactionResponse.getTotal());
  }

  @Test
  public void toResponse_shouldMapTimestamp() {
    LocalDate expectedTimestamp = createTimestamp();
    Transaction transaction = aTransaction().withTimestamp(expectedTimestamp).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTimestamp, transactionResponse.getTimestamp());
  }
}
