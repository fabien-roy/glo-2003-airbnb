package ca.ulaval.glo2003.interfaces.rest;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.rest.handlers.BedNotFoundExceptionHandler;
import ca.ulaval.glo2003.beds.rest.handlers.InvalidBedTypeExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.JsonProcessingExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.RouteGroup;

public class ErrorResource implements RouteGroup {

  public static final String ERROR_PATH = "*";

  @Override
  public void addRoutes() {
    exception(Exception.class, new CatchallExceptionHandler());
    exception(JsonProcessingException.class, new JsonProcessingExceptionHandler());
    exception(InvalidBedTypeException.class, new InvalidBedTypeExceptionHandler());
    exception(BedNotFoundException.class, new BedNotFoundExceptionHandler());
  }
}
