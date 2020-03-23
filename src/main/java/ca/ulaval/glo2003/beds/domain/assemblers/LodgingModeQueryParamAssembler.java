package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import java.util.Map;

public class LodgingModeQueryParamAssembler implements BedQueryParamAssembler {

  public static final String LODGING_MODE_PARAM = "lodgingMode";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(LODGING_MODE_PARAM) != null
        ? builder.withLodgingMode(parseLodgingMode(params))
        : builder;
  }

  private LodgingModes parseLodgingMode(Map<String, String[]> params) {
    return LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]);
  }
}
