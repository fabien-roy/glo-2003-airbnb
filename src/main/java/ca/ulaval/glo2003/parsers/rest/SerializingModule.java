package ca.ulaval.glo2003.parsers.rest;

import ca.ulaval.glo2003.parsers.rest.serializers.AbstractDeserializer;
import ca.ulaval.glo2003.parsers.rest.serializers.AbstractSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;

public class SerializingModule extends SimpleModule {

  public SerializingModule(
      List<AbstractSerializer> serializers, List<AbstractDeserializer> deserializers) {
    serializers.forEach(serializer -> addSerializer(serializer.getType(), serializer));
    deserializers.forEach(deserializer -> addDeserializer(deserializer.getType(), deserializer));
  }
}
