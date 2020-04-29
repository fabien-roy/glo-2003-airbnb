package ca.ulaval.glo2003.transactions.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import ca.ulaval.glo2003.transactions.exceptions.TransactionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class TransactionExceptionHandler extends AbstractExceptionHandler<TransactionException> {

  private final Set<ErrorFactory<TransactionException>> factories;

  @Inject
  public TransactionExceptionHandler(
      ObjectMapper objectMapper, Set<ErrorFactory<TransactionException>> factories) {
    super(objectMapper);
    this.factories = factories;
  }

  @Override
  public void handle(TransactionException e, Request request, Response response) {
    handleIfCan(factories, e, response);
  }
}
