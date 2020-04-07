package ca.ulaval.glo2003.interfaces.domain;

import ca.ulaval.glo2003.interfaces.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.interfaces.domain.serializers.SerializingModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractMapper extends ObjectMapper {

  public AbstractMapper(
      SerializingModule serializingModule, DeserializingModule deserializingModule) {
    registerModule(serializingModule);
    registerModule(deserializingModule);
    disable(DeserializationFeature.WRAP_EXCEPTIONS);
    disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
  }
}
