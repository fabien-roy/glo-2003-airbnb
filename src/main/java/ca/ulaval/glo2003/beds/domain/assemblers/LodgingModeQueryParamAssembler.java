package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import java.util.List;
import java.util.Map;

public class LodgingModeQueryParamAssembler implements BedQueryParamAssembler {

  public static final String LODGING_MODE_PARAM = "lodgingMode";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> lodgingModes = params.get(LODGING_MODE_PARAM);

    return lodgingModes != null
        ? builder.withLodgingMode(LodgingModes.get(lodgingModes.get(0)))
        : builder;
  }
}
