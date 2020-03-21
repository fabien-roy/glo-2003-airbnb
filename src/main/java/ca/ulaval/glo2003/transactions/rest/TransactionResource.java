package ca.ulaval.glo2003.transactions.rest;

import static spark.Spark.get;

import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class TransactionResource implements RouteGroup {

  public static final String TRANSACTION_PATH = "/admin/transactions";

  private final TransactionService transactionService;

  @Inject
  public TransactionResource(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public void addRoutes() {
    get("", this::getAll, new ObjectMapper()::writeValueAsString);
  }

  public Object getAll(Request request, Response response) {
    List<TransactionResponse> transactionResponses = transactionService.getAll();
    response.status(HttpStatus.OK_200);
    return transactionResponses;
  }
}
