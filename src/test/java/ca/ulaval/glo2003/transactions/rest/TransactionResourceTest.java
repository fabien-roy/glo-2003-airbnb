package ca.ulaval.glo2003.transactions.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class TransactionResourceTest {

  TransactionService transactionService;
  TransactionResource transactionResource;

  @BeforeEach
  public void setUpResource() {
    transactionService = mock(TransactionService.class);
    transactionResource = new TransactionResource(transactionService);
  }

  @Test
  public void getAll_withOneTransaction_shouldReturnOnlyOneTransaction() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    TransactionResponse expectedTransactionResponse = mock(TransactionResponse.class);
    when(transactionService.getAll())
        .thenReturn(Collections.singletonList(expectedTransactionResponse));

    List<TransactionResponse> transactionResponses =
        (List<TransactionResponse>) transactionResource.getAll(request, response);

    assertEquals(1, transactionResponses.size());
    assertTrue(transactionResponses.contains(expectedTransactionResponse));
  }

  @Test
  public void getAll_withMultipleTransactions_shouldReturnAllTransactions() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    TransactionResponse expectedTransactionResponse = mock(TransactionResponse.class);
    TransactionResponse otherExpectedTransactionResponse = mock(TransactionResponse.class);
    when(transactionService.getAll())
        .thenReturn(Arrays.asList(expectedTransactionResponse, otherExpectedTransactionResponse));

    List<TransactionResponse> transactionResponses =
        (List<TransactionResponse>) transactionResource.getAll(request, response);

    assertEquals(2, transactionResponses.size());
    assertTrue(transactionResponses.contains(expectedTransactionResponse));
    assertTrue(transactionResponses.contains(otherExpectedTransactionResponse));
  }
}
