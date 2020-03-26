package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceDeserializer;
import com.google.inject.Inject;
import java.util.Arrays;

public class BedDeserializingModule extends DeserializingModule {

  @Inject
  public BedDeserializingModule(
      CapacityDeserializer capacityDeserializer,
      BloodTypesDeserializer bloodTypesDeserializer,
      PackagesDeserializer packagesDeserializer,
      PriceDeserializer priceDeserializer) {
    super(
        Arrays.asList(
            capacityDeserializer, bloodTypesDeserializer, capacityDeserializer, priceDeserializer));
  }
}
