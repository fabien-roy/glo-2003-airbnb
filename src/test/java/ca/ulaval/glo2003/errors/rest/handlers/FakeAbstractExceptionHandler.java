package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class FakeAbstractExceptionHandler extends AbstractExceptionHandler<Exception> {

  private final Set<ErrorFactory<Exception>> factories;

  @Inject
  public FakeAbstractExceptionHandler(Set<ErrorFactory<Exception>> factories) {
    this.factories = factories;
  }

  @Override
  public void handle(Exception exception, Request request, Response response) {
    handleIfCan(factories, exception, response);
  }
}
