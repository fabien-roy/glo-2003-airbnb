package ca.ulaval.glo2003.transactions.rest;

import static spark.Spark.get;
import static spark.Spark.post;

import ca.ulaval.glo2003.admin.rest.ServiceFeeRequest;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class TransactionResource implements RouteGroup {

  public static final String TRANSACTION_PATH = "/admin/transactions";

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @Inject
  public TransactionResource(
      TransactionService transactionService, TransactionMapper transactionMapper) {
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public void addRoutes() {
    get("", this::getAll, transactionMapper::writeValueAsString);
    post("", this::configureServiceFee);
  }

  public Object getAll(Request request, Response response) {
    List<TransactionResponse> transactionResponses = transactionService.getAllResponses();
    response.status(HttpStatus.OK_200);
    return transactionResponses;
  }

  public Response configureServiceFee(Request request, Response response) throws IOException {
    ServiceFeeRequest serviceFeeRequest =
        transactionMapper.readValue(request.body(), ServiceFeeRequest.class);

    transactionService.updateServiceFee(serviceFeeRequest);
    response.status(HttpStatus.OK_200);
    return response;
  }
}
