package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.SerializingModule;
import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingSerializer;
import java.util.List;
import javax.inject.Inject;

public class BedSerializingModule extends SerializingModule {

  @Inject
  public BedSerializingModule(List<ThrowingSerializer> serializers) {
    super(serializers);
  }
}
