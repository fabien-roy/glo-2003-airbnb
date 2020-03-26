package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.parsers.domain.AbstractParser;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionDeserializatingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionSerializingModule;
import javax.inject.Inject;

public class TransactionParser extends AbstractParser {

  @Inject
  public TransactionParser(
      TransactionSerializingModule serializingModule,
      TransactionDeserializatingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
