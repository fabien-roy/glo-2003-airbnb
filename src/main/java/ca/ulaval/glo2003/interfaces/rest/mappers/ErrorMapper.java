package ca.ulaval.glo2003.interfaces.rest.mappers;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.interfaces.exceptions.ExternalServiceException;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.ExternalServiceExceptionHandler;
import com.google.inject.Inject;
import spark.RouteGroup;

public class ErrorMapper implements RouteGroup {

  public static final String ERROR_PATH = "*";

  private final CatchallExceptionHandler catchallExceptionHandler;
  private final ExternalServiceExceptionHandler externalServiceExceptionHandler;
  private final BedExceptionHandler bedExceptionHandler;
  private final BookingExceptionHandler bookingExceptionHandler;

  @Inject
  public ErrorMapper(
      CatchallExceptionHandler catchallExceptionHandler,
      ExternalServiceExceptionHandler externalServiceExceptionHandler,
      BedExceptionHandler bedExceptionHandler,
      BookingExceptionHandler bookingExceptionHandler) {
    this.catchallExceptionHandler = catchallExceptionHandler;
    this.externalServiceExceptionHandler = externalServiceExceptionHandler;
    this.bedExceptionHandler = bedExceptionHandler;
    this.bookingExceptionHandler = bookingExceptionHandler;
  }

  @Override
  public void addRoutes() {
    exception(Exception.class, catchallExceptionHandler);
    exception(ExternalServiceException.class, externalServiceExceptionHandler);
    exception(BedException.class, bedExceptionHandler);
    exception(BookingException.class, bookingExceptionHandler);
  }
}
