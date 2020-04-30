package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.SerializingModule;
import java.util.Collections;
import javax.inject.Inject;

public class ConfigurationSerializingModule extends SerializingModule {

  @Inject
  public ConfigurationSerializingModule(ServiceFeeSerializer serviceFeeSerializer) {
    super(Collections.singletonList(serviceFeeSerializer));
  }
}
