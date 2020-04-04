package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.SerializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import java.util.Collections;
import javax.inject.Inject;

public class BookingSerializingModule extends SerializingModule {

  @Inject
  public BookingSerializingModule(PriceSerializer priceSerializer) {
    super(Collections.singletonList(priceSerializer));
  }
}
