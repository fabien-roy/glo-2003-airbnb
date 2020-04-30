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
import ca.ulaval.glo2003.transactions.exceptions.TransactionException;
import ca.ulaval.glo2003.transactions.rest.handlers.TransactionExceptionHandler;
import com.google.inject.Inject;
import spark.RouteGroup;

public class ErrorMapper implements RouteGroup {

  public static final String ERROR_PATH = "*";

  private final CatchallExceptionHandler catchallExceptionHandler;
  private final TransactionExceptionHandler transactionExceptionHandler;
  private final ReportExceptionHandler reportExceptionHandler;
  private final LocationExceptionHandler locationExceptionHandler;
  private final BedExceptionHandler bedExceptionHandler;
  private final BookingExceptionHandler bookingExceptionHandler;

  @Inject
  public ErrorMapper(
      CatchallExceptionHandler catchallExceptionHandler,
      TransactionExceptionHandler transactionExceptionHandler,
      ReportExceptionHandler reportExceptionHandler,
      LocationExceptionHandler locationExceptionHandler,
      BedExceptionHandler bedExceptionHandler,
      BookingExceptionHandler bookingExceptionHandler) {
    this.catchallExceptionHandler = catchallExceptionHandler;
    this.transactionExceptionHandler = transactionExceptionHandler;
    this.reportExceptionHandler = reportExceptionHandler;
    this.locationExceptionHandler = locationExceptionHandler;
    this.bedExceptionHandler = bedExceptionHandler;
    this.bookingExceptionHandler = bookingExceptionHandler;
  }

  @Override
  public void addRoutes() {
    exception(Exception.class, catchallExceptionHandler);
    exception(TransactionException.class, transactionExceptionHandler);
    exception(ReportException.class, reportExceptionHandler);
    exception(LocationException.class, locationExceptionHandler);
    exception(BedException.class, bedExceptionHandler);
    exception(BookingException.class, bookingExceptionHandler);
  }
}
