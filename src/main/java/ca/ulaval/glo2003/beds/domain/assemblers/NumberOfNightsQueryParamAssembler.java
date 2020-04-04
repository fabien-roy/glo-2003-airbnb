package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.interfaces.domain.assemblers.PositiveIntegerQueryParamAssembler;
import java.util.List;
import java.util.Map;

public class NumberOfNightsQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final int MAX_NUMBER_OF_NIGHTS = 90;
  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> numbersOfNights = params.get(NUMBER_OF_NIGHTS_PARAM);

    return numbersOfNights != null
        ? builder.withNumberOfNights(parseNumberOfNights(numbersOfNights.get(0)))
        : builder;
  }

  @Override
  public void throwException() {
    throw new InvalidNumberOfNightsException();
  }

  private int parseNumberOfNights(String value) {
    int numberOfNights = parsePositiveValue(value);

    if (numberOfNights > MAX_NUMBER_OF_NIGHTS) throwException();

    return numberOfNights;
  }
}
