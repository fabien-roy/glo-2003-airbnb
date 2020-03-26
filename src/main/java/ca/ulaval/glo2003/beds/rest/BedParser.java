package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.rest.AbstractParser;
import ca.ulaval.glo2003.parsers.rest.SerializingModule;
import java.util.List;

public class BedParser extends AbstractParser {

  public BedParser(List<SerializingModule> modules) {
    super(modules);
  }
}
