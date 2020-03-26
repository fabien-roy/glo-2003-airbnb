package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.parsers.domain.AbstractMapper;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionDeserializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionSerializingModule;
import javax.inject.Inject;

public class TransactionMapper extends AbstractMapper {

  @Inject
  public TransactionMapper(
      TransactionSerializingModule serializingModule,
      TransactionDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
