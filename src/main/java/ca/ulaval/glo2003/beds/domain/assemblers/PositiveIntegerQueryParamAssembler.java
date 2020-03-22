package ca.ulaval.glo2003.beds.domain.assemblers;

public abstract class PositiveIntegerQueryParamAssembler implements BedQueryParamAssembler {

  // TODO : Exceptions should be handled differently
  // TODO : Check if the exception thrown fit user stories
  protected int parsePositiveInteger(String integer, RuntimeException exception) {
    int parsedInteger;

    try {
      parsedInteger = Integer.parseInt(integer);
    } catch (NumberFormatException e) {
      throw exception;
    }

    if (parsedInteger < 0) {
      throw exception;
    }

    return parsedInteger;
  }
}
