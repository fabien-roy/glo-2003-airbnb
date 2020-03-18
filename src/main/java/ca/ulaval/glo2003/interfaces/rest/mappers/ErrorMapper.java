package ca.ulaval.glo2003.interfaces.rest.mappers;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.beds.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import spark.RouteGroup;

public class ErrorMapper implements RouteGroup {

  public static final String ERROR_PATH = "*";

  private final CatchallExceptionHandler catchallExceptionHandler;
  private final BedExceptionHandler bedExceptionHandler;
  private final BookingExceptionHandler bookingExceptionHandler;

  public ErrorMapper(
      CatchallExceptionHandler catchallExceptionHandler,
      BedExceptionHandler bedExceptionHandler,
      BookingExceptionHandler bookingExceptionHandler) {
    this.catchallExceptionHandler = catchallExceptionHandler;
    this.bedExceptionHandler = bedExceptionHandler;
    this.bookingExceptionHandler = bookingExceptionHandler;
  }

  @Override
  public void addRoutes() {
    exception(Exception.class, catchallExceptionHandler);
    exception(BedException.class, bedExceptionHandler);
    exception(BookingException.class, bookingExceptionHandler);
  }
}
