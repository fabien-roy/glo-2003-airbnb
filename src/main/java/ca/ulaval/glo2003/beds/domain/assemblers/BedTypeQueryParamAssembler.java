package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import java.util.List;
import java.util.Map;

public class BedTypeQueryParamAssembler implements BedQueryParamAssembler {

  public static final String BED_TYPE_PARAM = "bedType";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> bedTypes = params.get(BED_TYPE_PARAM);

    return bedTypes != null ? builder.withBedType(BedTypes.get(bedTypes.get(0))) : builder;
  }
}
