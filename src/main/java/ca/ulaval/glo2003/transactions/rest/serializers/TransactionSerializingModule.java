package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.SerializingModule;
import com.google.inject.Inject;
import java.util.Collections;

public class TransactionSerializingModule extends SerializingModule {

  @Inject
  public TransactionSerializingModule(PriceSerializer priceSerializer) {
    super(Collections.singletonList(priceSerializer));
  }
}
