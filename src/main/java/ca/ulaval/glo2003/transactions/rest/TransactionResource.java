package ca.ulaval.glo2003.transactions.rest;

import static spark.Spark.get;

import ca.ulaval.glo2003.admin.rest.ConfigurationRequest;
import ca.ulaval.glo2003.transactions.services.ConfigurationService;
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
  private final TransactionMapper transactionMapper;
  private final ConfigurationService configurationService;
  private final ConfigurationMapper configurationMapper;

  @Inject
  public TransactionResource(
      TransactionService transactionService,
      TransactionMapper transactionMapper,
      ConfigurationService configurationService,
      ConfigurationMapper configurationMapper) {
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
    this.configurationService = configurationService;
    this.configurationMapper = configurationMapper;
  }

  @Override
  public void addRoutes() {
    get("", this::getAll, transactionMapper::writeValueAsString);
  }

  public Object getAll(Request request, Response response) {
    List<TransactionResponse> transactionResponses = transactionService.getAllResponses();
    response.status(HttpStatus.OK_200);
    return transactionResponses;
  }

  public Response configure(Request request, Response response) {
    ConfigurationRequest configureRequest =
        configurationMapper.readValue(request.body(), ConfigurationRequest.class);
    configurationService.configure(configureRequest);
    response.status(HttpStatus.OK_200);
    return response;
  }
}
