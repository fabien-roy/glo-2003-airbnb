package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.SerializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import java.util.Collections;
import javax.inject.Inject;

public class BedSerializingModule extends SerializingModule {

  @Inject
  public BedSerializingModule(PriceSerializer priceSerializer) {
    super(Collections.singletonList(priceSerializer));
  }
}
