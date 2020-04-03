package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import java.util.List;
import java.util.Map;

public class NumberOfNightsQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> numbersOfNights = params.get(NUMBER_OF_NIGHTS_PARAM);

    return numbersOfNights != null
        ? builder.withNumberOfNights(parsePositiveValue(numbersOfNights.get(0)))
        : builder;
  }

  @Override
  protected void throwException() {
    throw new InvalidNumberOfNightsException();
  }
}
