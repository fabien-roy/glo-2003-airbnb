package ca.ulaval.glo2003.beds.domain.assemblers;

public abstract class PositiveDoubleQueryParamAssembler
    extends PositiveNumberQueryParamAssembler<Double> {

  protected abstract void throwException();

  @Override
  protected Double parseValue(String value) {
    return Double.parseDouble(value);
  }
}
