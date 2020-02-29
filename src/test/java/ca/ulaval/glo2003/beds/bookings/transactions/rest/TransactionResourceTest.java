package ca.ulaval.glo2003.beds.bookings.transactions.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.transactions.services.TransactionService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.QueryParamsMap;
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
  public void getAll_withOneBed_shouldReturnOnlyOneBeds() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    TransactionResponse expectedTransactionResponse = mock(TransactionResponse.class);
    when(request.queryMap()).thenReturn(mock(QueryParamsMap.class));
    when(transactionService.getAll()).thenReturn(Arrays.asList(expectedTransactionResponse));

    List<TransactionResponse> transactionResponses =
        (List<TransactionResponse>) transactionResource.getAll(request, response);

    assertEquals(1, transactionResponses.size());
    assertTrue(transactionResponses.contains(expectedTransactionResponse));
  }

  @Test
  public void getAll_withMultipleBeds_shouldReturnAllBeds() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    TransactionResponse expectedTransactionResponse = mock(TransactionResponse.class);
    TransactionResponse otherExpectedTransactionResponse = mock(TransactionResponse.class);
    when(request.queryMap()).thenReturn(mock(QueryParamsMap.class));
    when(transactionService.getAll())
        .thenReturn(Arrays.asList(expectedTransactionResponse, otherExpectedTransactionResponse));

    List<TransactionResponse> transactionResponses =
        (List<TransactionResponse>) transactionResource.getAll(request, response);

    assertEquals(2, transactionResponses.size());
    assertTrue(transactionResponses.contains(expectedTransactionResponse));
    assertTrue(transactionResponses.contains(otherExpectedTransactionResponse));
  }
}
