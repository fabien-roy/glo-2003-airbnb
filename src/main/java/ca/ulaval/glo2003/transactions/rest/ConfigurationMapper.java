package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.interfaces.domain.AbstractMapper;
import ca.ulaval.glo2003.transactions.rest.serializers.ConfigurationDeserializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.ConfigurationSerializingModule;
import javax.inject.Inject;

public class ConfigurationMapper extends AbstractMapper {

  @Inject
  public ConfigurationMapper(
      ConfigurationSerializingModule serializingModule,
      ConfigurationDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
