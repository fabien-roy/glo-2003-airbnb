package ca.ulaval.glo2003.parsers.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class ParsingExceptionHandler extends AbstractExceptionHandler<JsonMappingException> {

  private final Set<ErrorFactory<JsonMappingException>> factories;

  @Inject
  public ParsingExceptionHandler(Set<ErrorFactory<JsonMappingException>> factories) {
    this.factories = factories;
  }

  @Override
  public void handle(JsonMappingException exception, Request request, Response response) {
    handleIfCan(factories, exception, response);
  }
}
