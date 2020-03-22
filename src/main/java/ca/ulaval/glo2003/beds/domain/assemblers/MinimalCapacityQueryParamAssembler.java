package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import java.util.Map;

public class MinimalCapacityQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String MIN_CAPACITY_PARAM = "minCapacity";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(MIN_CAPACITY_PARAM) != null
        ? builder.withMinCapacity(parseMinCapacity(params.get(MIN_CAPACITY_PARAM)[0]))
        : builder;
  }

  private int parseMinCapacity(String minCapacity) {
    return parsePositiveInteger(minCapacity, new InvalidCapacityException());
  }
}
