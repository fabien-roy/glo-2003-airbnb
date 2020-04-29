package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.DeserializingModule;
import com.google.inject.Inject;
import java.util.Collections;

public class TransactionDeserializingModule extends DeserializingModule {

  @Inject
  public TransactionDeserializingModule(ServiceFeeDeserializer serviceFeeDeserializer) {
    super(Collections.singletonList(serviceFeeDeserializer));
  }
}
