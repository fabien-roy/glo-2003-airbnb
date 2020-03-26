package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.locations.rest.serializers.ZipCodeDeserializer;
import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceDeserializer;
import com.google.inject.Inject;
import java.util.Arrays;

public class BedDeserializingModule extends DeserializingModule {

  @Inject
  public BedDeserializingModule(
      PublicKeyDeserializer publicKeyDeserializer,
      ZipCodeDeserializer zipCodeDeserializer,
      BedTypeDeserializer bedTypeDeserializer,
      CleaningFrequencyDeserializer cleaningFrequencyDeserializer,
      CapacityDeserializer capacityDeserializer,
      BloodTypesDeserializer bloodTypesDeserializer,
      PackagesDeserializer packagesDeserializer,
      PackageNameDeserializer packageNameDeserializer,
      PriceDeserializer priceDeserializer) {
    super(
        Arrays.asList(
            publicKeyDeserializer,
            zipCodeDeserializer,
            bedTypeDeserializer,
            cleaningFrequencyDeserializer,
            capacityDeserializer,
            bloodTypesDeserializer,
            packageNameDeserializer,
            packagesDeserializer,
            priceDeserializer));
  }
}
