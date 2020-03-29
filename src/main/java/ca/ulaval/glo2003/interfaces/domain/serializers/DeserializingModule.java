package ca.ulaval.glo2003.interfaces.domain.serializers;

import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;

public abstract class DeserializingModule extends SimpleModule {

  public DeserializingModule(List<AbstractDeserializer> deserializers) {
    deserializers.forEach(deserializer -> addDeserializer(deserializer.getType(), deserializer));
  }
}
