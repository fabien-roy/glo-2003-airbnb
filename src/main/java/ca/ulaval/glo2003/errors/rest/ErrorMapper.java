package ca.ulaval.glo2003.errors.rest;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.errors.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.locations.exceptions.LocationException;
import ca.ulaval.glo2003.locations.rest.handlers.LocationExceptionHandler;
import ca.ulaval.glo2003.reports.exceptions.ReportException;
import ca.ulaval.glo2003.reports.rest.handlers.ReportExceptionHandler;
import com.google.inject.Inject;
import spark.RouteGroup;

public class ErrorMapper implements RouteGroup {

  public static final String ERROR_PATH = "*";

  private final CatchallExceptionHandler catchallExceptionHandler;
  private final LocationExceptionHandler locationExceptionHandler;
  private final BedExceptionHandler bedExceptionHandler;
  private final BookingExceptionHandler bookingExceptionHandler;
  private final ReportExceptionHandler reportExceptionHandler;

  @Inject
  public ErrorMapper(
      CatchallExceptionHandler catchallExceptionHandler,
      LocationExceptionHandler locationExceptionHandler,
      BedExceptionHandler bedExceptionHandler,
      BookingExceptionHandler bookingExceptionHandler,
      ReportExceptionHandler reportExceptionHandler) {
    this.catchallExceptionHandler = catchallExceptionHandler;
    this.locationExceptionHandler = locationExceptionHandler;
    this.bedExceptionHandler = bedExceptionHandler;
    this.bookingExceptionHandler = bookingExceptionHandler;
    this.reportExceptionHandler = reportExceptionHandler;
  }

  @Override
  public void addRoutes() {
    exception(Exception.class, catchallExceptionHandler);
    exception(LocationException.class, locationExceptionHandler);
    exception(BedException.class, bedExceptionHandler);
    exception(BookingException.class, bookingExceptionHandler);
    exception(ReportException.class, reportExceptionHandler);
  }
}
