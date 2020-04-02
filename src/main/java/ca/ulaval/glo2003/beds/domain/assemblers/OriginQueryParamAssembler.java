package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class OriginQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ORIGIN_PARAM = "origin";

  private final LocationService locationService;

  @Inject
  public OriginQueryParamAssembler(LocationService locationService) {
    this.locationService = locationService;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> origins = params.get(ORIGIN_PARAM);

    return origins != null ? builder.withOrigin(parseOrigin(origins.get(0))) : builder;
  }

  public Location parseOrigin(String origin) {
    return locationService.getLocation(origin);
  }
}
