package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.admin.rest.ServiceFeeDeserializer;
import ca.ulaval.glo2003.interfaces.domain.serializers.DeserializingModule;
import java.util.Collections;
import javax.inject.Inject;

public class ConfigurationDeserializingModule extends DeserializingModule {

  @Inject
  public ConfigurationDeserializingModule(ServiceFeeDeserializer serviceFeeDeserializer) {
    super(Collections.singletonList(serviceFeeDeserializer));
  }
}
