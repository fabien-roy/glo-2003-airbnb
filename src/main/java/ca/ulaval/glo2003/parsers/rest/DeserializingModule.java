package ca.ulaval.glo2003.parsers.rest;

import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;

public class DeserializingModule extends SimpleModule {

  public DeserializingModule(List<AbstractDeserializer> deserializers) {
    deserializers.forEach(deserializer -> addDeserializer(deserializer.getType(), deserializer));
  }
}
