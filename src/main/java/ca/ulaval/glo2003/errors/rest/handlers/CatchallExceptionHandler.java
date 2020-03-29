package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler extends DefaultExceptionHandler<Exception> {

  private final Set<ErrorFactory<Exception>> factories;

  @Inject
  public CatchallExceptionHandler(
      DefaultErrorFactory defaultFactory, Set<ErrorFactory<Exception>> catchallFactories) {
    super(defaultFactory);
    this.factories = catchallFactories;
  }

  @Override
  public void handle(Exception exception, Request request, Response response) {
    handleIfCan(factories, exception, response);
  }
}
