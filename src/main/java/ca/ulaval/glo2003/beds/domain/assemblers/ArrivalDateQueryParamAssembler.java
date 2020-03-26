package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.converters.BookingDateConverter;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import java.util.Map;
import javax.inject.Inject;

public class ArrivalDateQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";

  private final BookingDateConverter bookingDateConverter;

  @Inject
  public ArrivalDateQueryParamAssembler(BookingDateConverter bookingDateConverter) {
    this.bookingDateConverter = bookingDateConverter;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(ARRIVAL_DATE_PARAM) != null
        ? builder.withArrivalDate(parseArrivalDate(params))
        : builder;
  }

  private BookingDate parseArrivalDate(Map<String, String[]> params) {
    return bookingDateConverter.fromString(params.get(ARRIVAL_DATE_PARAM)[0]);
  }
}
