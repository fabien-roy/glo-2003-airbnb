package ca.ulaval.glo2003.transactions.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class TransactionResourceTest {

  private static TransactionResource transactionResource;
  private static TransactionService transactionService = mock(TransactionService.class);
  private static TransactionMapper transactionMapper = mock(TransactionMapper.class);

  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static ServiceFeeRequest serviceFeeRequest = mock(ServiceFeeRequest.class);
  private static TransactionResponse transactionResponse = mock(TransactionResponse.class);
  private static TransactionResponse otherTransactionResponse = mock(TransactionResponse.class);

  @BeforeAll
  public static void setUpResource() {
    transactionResource = new TransactionResource(transactionService, transactionMapper);
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    setUpMocksForUpdateServiceFee();
    setUpMocksForGetAll();
  }

  public void setUpMocksForUpdateServiceFee() throws IOException {
    String requestBody = "requestBody";
    when(request.body()).thenReturn(requestBody);
    when(transactionMapper.readValue(requestBody, ServiceFeeRequest.class))
        .thenReturn(serviceFeeRequest);
  }

  private void setUpMocksForGetAll() {
    reset(response);
    when(transactionService.getAllResponses())
        .thenReturn(Arrays.asList(transactionResponse, otherTransactionResponse));
  }

  @Test
  public void getAll_shouldReturnTransactions() {
    List<TransactionResponse> transactionResponses =
        (List<TransactionResponse>) transactionResource.getAll(request, response);

    assertEquals(2, transactionResponses.size());
    assertTrue(transactionResponses.contains(transactionResponse));
    assertTrue(transactionResponses.contains(otherTransactionResponse));
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    transactionResource.getAll(request, response);

    verify(response).status(HttpStatus.OK_200);
  }

  @Test
  public void updateServiceFee_shouldUpdateServiceFee() throws IOException {
    transactionResource.configureServiceFee(request, response);

    verify(transactionService).updateServiceFee(serviceFeeRequest);
  }

  @Test
  public void updateServiceFee_shouldSetOkAsHttpStatus() throws IOException {
    transactionResource.configureServiceFee(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
