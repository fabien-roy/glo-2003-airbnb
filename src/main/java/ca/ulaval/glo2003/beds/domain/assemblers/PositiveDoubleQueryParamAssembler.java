package ca.ulaval.glo2003.beds.domain.assemblers;

public abstract class PositiveDoubleQueryParamAssembler implements BedQueryParamAssembler {

  protected abstract void throwException();

  protected double parsePositiveDouble(String value) {
    double parsedValue = -1;

    try {
      parsedValue = Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throwException();
    }

    if (parsedValue <= 0) {
      throwException();
    }

    return parsedValue;
  }
}
