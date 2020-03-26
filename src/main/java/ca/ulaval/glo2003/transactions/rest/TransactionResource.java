package ca.ulaval.glo2003.transactions.rest;

import static spark.Spark.get;

import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class TransactionResource implements RouteGroup {

  public static final String TRANSACTION_PATH = "/admin/transactions";

  private final TransactionService transactionService;
  private final TransactionParser transactionParser;

  @Inject
  public TransactionResource(
      TransactionService transactionService, TransactionParser transactionParser) {
    this.transactionService = transactionService;
    this.transactionParser = transactionParser;
  }

  @Override
  public void addRoutes() {
    get("", this::getAll, transactionParser::writeValueAsString);
  }

  public Object getAll(Request request, Response response) {
    List<TransactionResponse> transactionResponses = transactionService.getAll();
    response.status(HttpStatus.OK_200);
    return transactionResponses;
  }
}
