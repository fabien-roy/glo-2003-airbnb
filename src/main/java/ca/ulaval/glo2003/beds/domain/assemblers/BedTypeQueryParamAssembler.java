package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import java.util.Map;

public class BedTypeQueryParamAssembler implements BedQueryParamAssembler {

  public static final String BED_TYPE_PARAM = "bedType";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(BED_TYPE_PARAM) != null ? builder.withBedType(getBedType(params)) : builder;
  }

  private BedTypes getBedType(Map<String, String[]> params) {
    return BedTypes.get(params.get(BED_TYPE_PARAM)[0]);
  }
}
