package ca.ulaval.glo2003.transactions.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class TransactionResourceTest {

  // TODO : Refactor this test class

  private static TransactionResource transactionResource;
  private static TransactionService transactionService = mock(TransactionService.class);
  private static TransactionMapper transactionParser = mock(TransactionMapper.class);

  @BeforeAll
  public static void setUpResource() {
    transactionResource = new TransactionResource(transactionService, transactionParser);
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
