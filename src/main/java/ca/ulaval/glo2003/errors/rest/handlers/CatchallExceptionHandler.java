package ca.ulaval.glo2003.errors.rest.handlers;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.InvalidFormatExceptionFactory;
import ca.ulaval.glo2003.errors.rest.factories.JsonProcessingExceptionFactory;
import com.google.inject.multibindings.Multibinder;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler implements ExceptionHandler<Exception> {

  private final Multibinder<ErrorFactory> factories;

  @Inject
  public CatchallExceptionHandler(Multibinder<ErrorFactory> factories) {
    this.factories = factories;
    factories.addBinding().to(InvalidFormatExceptionFactory.class);
    factories.addBinding().to(JsonProcessingExceptionFactory.class);
  }

  @Override
  public void handle(Exception e, Request request, Response response) {
    List<ErrorFactory> factories = null;
    String errorResponse;
    int status;

    Optional<ErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    if (foundFactory.isPresent()) {
      errorResponse = foundFactory.get().createResponse();
      status = foundFactory.get().createStatus();
    } else {
      status = defaultStatus();
      errorResponse = defaultBody();
    }

    response.status(status);
    response.body(errorResponse);
  }

  private static int defaultStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }

  private static String defaultBody() {
    return tryWriteValueAsString("BAD_REQUEST", "could not parse JSON");
  }
}
