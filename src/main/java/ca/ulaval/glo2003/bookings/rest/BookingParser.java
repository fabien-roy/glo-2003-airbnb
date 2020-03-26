package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.bookings.rest.serializers.BookingDeserializingModule;
import ca.ulaval.glo2003.bookings.rest.serializers.BookingSerializingModule;
import ca.ulaval.glo2003.parsers.domain.AbstractParser;
import javax.inject.Inject;

public class BookingParser extends AbstractParser {

  @Inject
  public BookingParser(
      BookingSerializingModule serializingModule, BookingDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
