package ca.ulaval.glo2003.interfaces.domain.assemblers;

public abstract class PositiveDoubleQueryParamAssembler
    extends PositiveNumberQueryParamAssembler<Double> {

  public abstract void throwException();

  @Override
  protected Double parseValue(String value) {
    return Double.parseDouble(value);
  }
}
