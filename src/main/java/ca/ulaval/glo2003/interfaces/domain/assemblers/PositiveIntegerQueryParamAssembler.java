package ca.ulaval.glo2003.interfaces.domain.assemblers;

public abstract class PositiveIntegerQueryParamAssembler
    extends PositiveNumberQueryParamAssembler<Integer> {

  public abstract void throwException();

  @Override
  protected Integer parseValue(String value) {
    return Integer.parseInt(value);
  }
}
