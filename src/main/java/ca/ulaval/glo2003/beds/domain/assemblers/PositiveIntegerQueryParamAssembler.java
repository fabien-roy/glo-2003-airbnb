package ca.ulaval.glo2003.beds.domain.assemblers;

public abstract class PositiveIntegerQueryParamAssembler implements BedQueryParamAssembler {

  protected abstract void throwException();

  protected int parsePositiveInteger(String integer) {
    int parsedInteger = -1;

    try {
      parsedInteger = Integer.parseInt(integer);
    } catch (NumberFormatException e) {
      throwException();
    }

    if (parsedInteger < 0) {
      throwException();
    }

    return parsedInteger;
  }
}
