package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.parsers.rest.AbstractParser;
import ca.ulaval.glo2003.parsers.rest.SerializingModule;
import java.util.List;

public class TransactionParser extends AbstractParser {

  public TransactionParser(List<SerializingModule> modules) {
    super(modules);
  }
}
