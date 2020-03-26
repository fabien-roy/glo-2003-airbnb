package ca.ulaval.glo2003.parsers.domain.serializers;

import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;

public abstract class SerializingModule extends SimpleModule {

  public SerializingModule(List<ThrowingSerializer> serializers) {
    serializers.forEach(serializer -> addSerializer(serializer.getType(), serializer));
  }
}
