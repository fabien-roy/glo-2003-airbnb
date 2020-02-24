package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionMapperTest {

  private TransactionMapper transactionMapper;

  @BeforeEach
  public void setUpMapper() {
    transactionMapper = new TransactionMapper();
  }

  @Test
  public void toResponse_withValidParameters_shouldReturnTransactionWithValidParameters() {}
}
