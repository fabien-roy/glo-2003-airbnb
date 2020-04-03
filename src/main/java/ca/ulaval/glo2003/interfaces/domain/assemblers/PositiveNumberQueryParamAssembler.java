package ca.ulaval.glo2003.interfaces.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;

public abstract class PositiveNumberQueryParamAssembler<T extends Number>
    implements BedQueryParamAssembler {

  protected abstract void throwException();

  protected abstract T parseValue(String value);

  protected T parsePositiveValue(String value) {
    T parsedValue = null;

    try {
      parsedValue = parseValue(value);
    } catch (NumberFormatException e) {
      throwException();
    }

    if (parsedValue != null && parsedValue.doubleValue() <= 0) {
      throwException();
    }

    return parsedValue;
  }
}
