package ca.ulaval.glo2003.interfaces;

import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.ExternalServiceExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.mappers.ErrorMapper;
import com.google.inject.AbstractModule;

public class ErrorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CatchallExceptionHandler.class);
    bind(ExternalServiceExceptionHandler.class);
    bind(BedExceptionHandler.class);
    bind(BookingExceptionHandler.class);
    bind(ErrorMapper.class);
  }
}
