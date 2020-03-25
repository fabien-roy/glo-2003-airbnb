package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.rest.AbstractParser;
import ca.ulaval.glo2003.parsers.rest.DeserializingModule;
import java.util.List;

public class BedParser extends AbstractParser {

  public BedParser(List<DeserializingModule> modules) {
    super(modules);
  }
}
