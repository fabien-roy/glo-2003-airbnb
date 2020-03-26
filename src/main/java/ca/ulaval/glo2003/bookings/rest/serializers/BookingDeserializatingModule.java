package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingDeserializer;
import com.google.inject.Inject;
import java.util.List;

public class BookingDeserializatingModule extends DeserializingModule {

  @Inject
  public BookingDeserializatingModule(List<ThrowingDeserializer> deserializers) {
    super(deserializers);
  }
}
