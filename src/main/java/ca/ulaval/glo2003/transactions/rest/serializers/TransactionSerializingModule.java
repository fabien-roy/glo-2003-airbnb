package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.SerializingModule;
import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingSerializer;
import com.google.inject.Inject;
import java.util.List;

public class TransactionSerializingModule extends SerializingModule {

  @Inject
  public TransactionSerializingModule(List<ThrowingSerializer> deserializers) {
    super(deserializers);
  }
}
