package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidMinimalCapacityException;
import java.util.List;
import java.util.Map;

public class MinimalCapacityQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String MIN_CAPACITY_PARAM = "minCapacity";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> minimalCapacities = params.get(MIN_CAPACITY_PARAM);

    return minimalCapacities != null
        ? builder.withMinCapacity(parsePositiveInteger(minimalCapacities.get(0)))
        : builder;
  }

  @Override
  protected void throwException() {
    throw new InvalidMinimalCapacityException();
  }
}
