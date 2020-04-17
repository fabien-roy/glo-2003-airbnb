package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.converters.BookingDateConverter;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ArrivalDateQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";

  private final BookingDateConverter bookingDateConverter;

  @Inject
  public ArrivalDateQueryParamAssembler(BookingDateConverter bookingDateConverter) {
    this.bookingDateConverter = bookingDateConverter;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> arrivalDates = params.get(ARRIVAL_DATE_PARAM);

    return arrivalDates != null
        ? builder.withArrivalDate(parseArrivalDate(arrivalDates.get(0)))
        : builder;
  }

  private ReservationDate parseArrivalDate(String arrivalDate) {
    return bookingDateConverter.fromString(arrivalDate);
  }
}
