package ca.ulaval.glo2003.parsers.domain;

import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.parsers.domain.serializers.SerializingModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractParser extends ObjectMapper {

  public AbstractParser(
      SerializingModule serializingModule, DeserializingModule deserializingModule) {
    registerModule(serializingModule);
    registerModule(deserializingModule);
    disable(DeserializationFeature.WRAP_EXCEPTIONS);
  }
}
