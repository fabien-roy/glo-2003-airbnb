package ca.ulaval.glo2003.bookings;

import ca.ulaval.glo2003.bookings.mappers.BookingMapper;
import ca.ulaval.glo2003.bookings.rest.BookingResource;
import ca.ulaval.glo2003.bookings.services.BookingService;
import ca.ulaval.glo2003.bookings.services.CancelationService;
import com.google.inject.AbstractModule;

public class BookingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(BookingService.class);
    bind(CancelationService.class);
    bind(BookingMapper.class);
    bind(BookingResource.class);
  }
}
