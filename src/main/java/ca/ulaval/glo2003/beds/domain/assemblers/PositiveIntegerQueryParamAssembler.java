package ca.ulaval.glo2003.beds.domain.assemblers;

public abstract class PositiveIntegerQueryParamAssembler
    extends PositiveNumberQueryParamAssembler<Integer> {

  protected abstract void throwException();

  @Override
  protected Integer parseValue(String value) {
    return Integer.parseInt(value);
  }
}
