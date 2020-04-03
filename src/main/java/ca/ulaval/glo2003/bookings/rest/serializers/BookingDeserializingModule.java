package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.beds.rest.serializers.PublicKeyDeserializer;
import ca.ulaval.glo2003.interfaces.domain.serializers.DeserializingModule;
import com.google.inject.Inject;
import java.util.Arrays;

public class BookingDeserializingModule extends DeserializingModule {

  @Inject
  public BookingDeserializingModule(
      PublicKeyDeserializer publicKeyDeserializer,
      ArrivalDateDeserializer arrivalDateDeserializer,
      ColonySizeDeserializer colonySizeDeserializer,
      NumberOfNightsDeserializer numberOfNightsDeserializer) {
    super(
        Arrays.asList(
            publicKeyDeserializer,
            arrivalDateDeserializer,
            colonySizeDeserializer,
            numberOfNightsDeserializer));
  }
}
