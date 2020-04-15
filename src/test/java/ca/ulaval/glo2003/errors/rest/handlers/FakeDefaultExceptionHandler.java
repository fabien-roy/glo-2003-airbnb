package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class FakeDefaultExceptionHandler extends DefaultExceptionHandler<Exception> {

  private final Set<ErrorFactory<Exception>> factories;

  @Inject
  public FakeDefaultExceptionHandler(
      ObjectMapper objectMapper,
      DefaultErrorFactory defaultFactory,
      Set<ErrorFactory<Exception>> factories) {
    super(objectMapper, defaultFactory);
    this.factories = factories;
  }

  @Override
  public void handle(Exception exception, Request request, Response response) {
    handleIfCan(factories, exception, response);
  }
}
