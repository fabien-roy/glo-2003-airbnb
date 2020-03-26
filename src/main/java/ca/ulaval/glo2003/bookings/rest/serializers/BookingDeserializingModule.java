package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.DeserializingModule;
import com.google.inject.Inject;
import java.util.Arrays;

public class BookingDeserializingModule extends DeserializingModule {

  @Inject
  public BookingDeserializingModule(
      ColonySizeDeserializer colonySizeDeserializer,
      NumberOfNightsDeserializer numberOfNightsDeserializer) {
    super(Arrays.asList(colonySizeDeserializer, numberOfNightsDeserializer));
  }
}
