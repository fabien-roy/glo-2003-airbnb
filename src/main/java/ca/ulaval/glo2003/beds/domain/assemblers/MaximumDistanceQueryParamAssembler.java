package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import java.util.List;
import java.util.Map;

public class MaximumDistanceQueryParamAssembler extends PositiveDoubleQueryParamAssembler {

  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> maxDistances = params.get(MAX_DISTANCE_PARAM);

    return maxDistances != null
        ? builder.withMaxDistance(parsePositiveValue(maxDistances.get(0)))
        : builder;
  }

  @Override
  protected void throwException() {
    throw new InvalidMaxDistanceException();
  }
}
