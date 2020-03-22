package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.rest.mappers.BookingDateMapper;
import java.util.Map;
import javax.inject.Inject;

public class ArrivalDateQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";

  private final BookingDateMapper bookingDateMapper;

  @Inject
  public ArrivalDateQueryParamAssembler(BookingDateMapper bookingDateMapper) {
    this.bookingDateMapper = bookingDateMapper;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(ARRIVAL_DATE_PARAM) != null
        ? builder.withArrivalDate(parseArrivalDate(params))
        : builder;
  }

  private BookingDate parseArrivalDate(Map<String, String[]> params) {
    return bookingDateMapper.fromString(params.get(ARRIVAL_DATE_PARAM)[0]);
  }
}
