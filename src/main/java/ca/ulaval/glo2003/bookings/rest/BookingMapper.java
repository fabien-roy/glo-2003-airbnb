package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.bookings.rest.serializers.BookingDeserializingModule;
import ca.ulaval.glo2003.bookings.rest.serializers.BookingSerializingModule;
import ca.ulaval.glo2003.parsers.domain.AbstractMapper;
import javax.inject.Inject;

public class BookingMapper extends AbstractMapper {

  @Inject
  public BookingMapper(
      BookingSerializingModule serializingModule, BookingDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
