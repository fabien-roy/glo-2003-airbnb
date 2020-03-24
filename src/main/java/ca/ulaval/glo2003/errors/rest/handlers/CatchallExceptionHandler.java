package ca.ulaval.glo2003.errors.rest.handlers;


import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.DefaultExceptionFactory;
import ca.ulaval.glo2003.errors.rest.factories.InvalidFormatExceptionFactory;
import ca.ulaval.glo2003.errors.rest.factories.JsonProcessingExceptionFactory;
import com.google.inject.multibindings.Multibinder;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler implements ExceptionHandler<Exception> {

  private final Multibinder<ErrorFactory> factories;
  private final DefaultExceptionFactory defaultFactory;

  @Inject
  public CatchallExceptionHandler(
      Multibinder<ErrorFactory> factories, DefaultExceptionFactory defaultFactory) {
    this.factories = factories;
    factories.addBinding().to(InvalidFormatExceptionFactory.class);
    factories.addBinding().to(JsonProcessingExceptionFactory.class);
    this.defaultFactory = defaultFactory;
  }

  @Override
  public void handle(Exception e, Request request, Response response) {
    List<ErrorFactory> factories = null;
    String errorResponse;
    int status;

    Optional<ErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    if (foundFactory.isPresent()) {
      status = foundFactory.get().createStatus();
      errorResponse = foundFactory.get().createResponse();
    } else {
      status = defaultFactory.createStatus();
      errorResponse = defaultFactory.createResponse();
    }

    response.status(status);
    response.body(errorResponse);
  }
}
