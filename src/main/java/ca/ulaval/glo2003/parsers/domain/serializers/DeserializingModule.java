package ca.ulaval.glo2003.parsers.domain.serializers;

import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;

public abstract class DeserializingModule extends SimpleModule {

  public DeserializingModule(List<ThrowingDeserializer> deserializers) {
    deserializers.forEach(deserializer -> addDeserializer(deserializer.getType(), deserializer));
  }
}
