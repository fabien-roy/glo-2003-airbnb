package ca.ulaval.glo2003.errors;

import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.errors.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.errors.rest.mappers.ErrorMapper;
import ca.ulaval.glo2003.locations.rest.handlers.LocationServiceExceptionHandler;
import com.google.inject.AbstractModule;

public class ErrorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CatchallExceptionHandler.class);
    bind(LocationServiceExceptionHandler.class);
    bind(BedExceptionHandler.class);
    bind(BookingExceptionHandler.class);
    bind(ErrorMapper.class);
  }
}
