package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class BedExceptionHandler extends AbstractExceptionHandler<BedException> {

  private final Set<ErrorFactory<BedException>> factories;

  @Inject
  public BedExceptionHandler(ObjectMapper objectMapper, Set<ErrorFactory<BedException>> factories) {
    super(objectMapper);
    this.factories = factories;
  }

  @Override
  public void handle(BedException e, Request request, Response response) {
    handleIfCan(factories, e, response);
  }
}
