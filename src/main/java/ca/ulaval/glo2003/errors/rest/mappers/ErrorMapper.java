package ca.ulaval.glo2003.errors.rest.mappers;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.errors.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.locations.exceptions.LocationException;
import ca.ulaval.glo2003.locations.rest.handlers.LocationExceptionHandler;
import ca.ulaval.glo2003.parsers.rest.handlers.ParsingExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.Inject;
import spark.RouteGroup;

public class ErrorMapper implements RouteGroup {

  public static final String ERROR_PATH = "*";

  private final CatchallExceptionHandler catchallExceptionHandler;
  private final ParsingExceptionHandler parsingExceptionHandler;
  private final LocationExceptionHandler locationExceptionHandler;
  private final BedExceptionHandler bedExceptionHandler;
  private final BookingExceptionHandler bookingExceptionHandler;

  @Inject
  public ErrorMapper(
      CatchallExceptionHandler catchallExceptionHandler,
      ParsingExceptionHandler parsingExceptionHandler,
      LocationExceptionHandler locationExceptionHandler,
      BedExceptionHandler bedExceptionHandler,
      BookingExceptionHandler bookingExceptionHandler) {
    this.catchallExceptionHandler = catchallExceptionHandler;
    this.parsingExceptionHandler = parsingExceptionHandler;
    this.locationExceptionHandler = locationExceptionHandler;
    this.bedExceptionHandler = bedExceptionHandler;
    this.bookingExceptionHandler = bookingExceptionHandler;
  }

  @Override
  public void addRoutes() {
    exception(Exception.class, catchallExceptionHandler);
    exception(JsonMappingException.class, parsingExceptionHandler);
    exception(LocationException.class, locationExceptionHandler);
    exception(BedException.class, bedExceptionHandler);
    exception(BookingException.class, bookingExceptionHandler);
  }
}
