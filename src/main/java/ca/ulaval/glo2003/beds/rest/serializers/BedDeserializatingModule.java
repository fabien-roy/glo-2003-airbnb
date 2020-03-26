package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingDeserializer;
import com.google.inject.Inject;
import java.util.List;

public class BedDeserializatingModule extends DeserializingModule {

  @Inject
  public BedDeserializatingModule(List<ThrowingDeserializer> deserializers) {
    super(deserializers);
  }
}
