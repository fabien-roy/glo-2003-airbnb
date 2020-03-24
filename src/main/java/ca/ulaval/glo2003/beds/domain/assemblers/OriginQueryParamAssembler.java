package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
import java.util.Map;
import javax.inject.Inject;

public class OriginQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ORIGIN_PARAM = "origin";

  private final LocationService locationService;

  // TODO : Should query param assembler know about location service?
  @Inject
  public OriginQueryParamAssembler(LocationService locationService) {
    this.locationService = locationService;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(ORIGIN_PARAM) != null ? builder.withOrigin(parseOrigin(params)) : builder;
  }

  public Location parseOrigin(Map<String, String[]> params) {
    return locationService.getLocation(params.get(ORIGIN_PARAM)[0]);
  }
}
