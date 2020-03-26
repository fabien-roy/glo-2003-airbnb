package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.bookings.rest.serializers.BookingDeserializatingModule;
import ca.ulaval.glo2003.bookings.rest.serializers.BookingSerializingModule;
import ca.ulaval.glo2003.parsers.domain.AbstractParser;
import javax.inject.Inject;

public class BookingParser extends AbstractParser {

  @Inject
  public BookingParser(
      BookingSerializingModule serializingModule,
      BookingDeserializatingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
