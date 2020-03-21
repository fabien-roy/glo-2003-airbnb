package ca.ulaval.glo2003.beds.bookings;

import ca.ulaval.glo2003.beds.bookings.rest.BookingResource;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.services.BookingService;
import com.google.inject.AbstractModule;

public class BookingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(BookingService.class);
    bind(BookingMapper.class);
    bind(BookingResource.class);
  }
}
