package ca.ulaval.glo2003.beds.bookings.transactions.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.transactions.services.TransactionService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionResourceTest {

  TransactionService transactionService;
  TransactionResource transactionResource;

  @BeforeEach
  public void setUpResource() {
    transactionService = mock(TransactionService.class);
    transactionResource = new TransactionResource(transactionService);
  }

  @Test
  public void getAll_shouldBeTheSameAsGetAllTransactionService() {
    ;
    TransactionResponse transactionResponse = mock(TransactionResponse.class);
    when(transactionService.getAll()).thenReturn(Collections.singletonList(transactionResponse));
    assertSame(transactionResource.getAll(), transactionService.getAll());
  }
}
