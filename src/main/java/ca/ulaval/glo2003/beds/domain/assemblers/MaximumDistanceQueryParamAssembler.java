package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import java.util.Map;

public class MaximumDistanceQueryParamAssembler extends PositiveIntegerQueryParamAssembler {

  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(MAX_DISTANCE_PARAM) != null
        ? builder.withMaxDistance(parseMaxDistance(params))
        : builder;
  }

  private int parseMaxDistance(Map<String, String[]> params) {
    return parsePositiveInteger(
        params.get(MAX_DISTANCE_PARAM)[0], new InvalidMaxDistanceException());
  }
}
