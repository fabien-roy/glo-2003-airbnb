package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.admin.rest.ServiceFeeDeserializer;
import ca.ulaval.glo2003.interfaces.domain.serializers.DeserializingModule;
import java.util.Collections;

public class TransactionDeserializingModule extends DeserializingModule {

  public TransactionDeserializingModule(ServiceFeeDeserializer serviceFeeDeserializer) {
    super(Collections.singletonList(serviceFeeDeserializer));
  }
}
