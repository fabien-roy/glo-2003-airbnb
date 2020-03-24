package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import java.util.Map;

public class NumberOfNightsQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(NUMBER_OF_NIGHTS_PARAM) != null
        ? builder.withNumberOfNights(parseNumberOfNights(params))
        : builder;
  }

  private int parseNumberOfNights(Map<String, String[]> params) {
    return parsePositiveInteger(
        params.get(NUMBER_OF_NIGHTS_PARAM)[0], new InvalidNumberOfNightsException());
  }
}
