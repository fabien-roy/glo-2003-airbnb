package ca.ulaval.glo2003.interfaces.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.errors.exceptions.TestingException;
import java.util.List;
import java.util.Map;

public class FakePositiveIntegerQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String POSITIVE_INT_PARAM = "positiveInt";

  @Override
  public void throwException() {
    throw new TestingException();
  }

  @Override
  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    parsePositiveValue(params.get(POSITIVE_INT_PARAM).get(0));
    return builder;
  }
}
