package ca.ulaval.glo2003.transactions.rest.mappers;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.rest.mappers.PriceMapper;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {

  private TransactionMapper transactionMapper;
  private PriceMapper priceMapper;

  @BeforeEach
  public void setUpMapper() {
    priceMapper = mock(PriceMapper.class);
    transactionMapper = new TransactionMapper(priceMapper);
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
    when(priceMapper.toDouble(total)).thenReturn(expectedTotal);

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTotal, transactionResponse.getTotal());
  }

  @Test
  public void toResponse_shouldMapTimestamp() {
    LocalDateTime timestamp = createTimestamp();
    String expectedTimestamp = timestamp.format(DateTimeFormatter.ISO_DATE_TIME) + "Z";
    Transaction transaction = aTransaction().withTimestamp(timestamp).build();

    TransactionResponse transactionResponse = transactionMapper.toResponse(transaction);

    assertEquals(expectedTimestamp, transactionResponse.getTimestamp());
  }
}
