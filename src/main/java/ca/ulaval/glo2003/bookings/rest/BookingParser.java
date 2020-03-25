package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.parsers.rest.AbstractParser;
import ca.ulaval.glo2003.parsers.rest.DeserializingModule;
import java.util.List;

public class BookingParser extends AbstractParser {

  public BookingParser(List<DeserializingModule> modules) {
    super(modules);
  }
}
