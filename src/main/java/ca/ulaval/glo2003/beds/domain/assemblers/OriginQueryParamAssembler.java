package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import com.google.inject.Inject;
import java.util.Map;

public class OriginQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ORIGIN_PARAM = "origin";

  private final ZippopotamusClient
      zippopotamusClient; // TODO : Use an interface, like ZipCodeClient

  @Inject
  public OriginQueryParamAssembler(ZippopotamusClient zippopotamusClient) {
    this.zippopotamusClient = zippopotamusClient;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(ORIGIN_PARAM) != null ? builder.withOrigin(parseOrigin(params)) : builder;
  }

  public ZipCode parseOrigin(Map<String, String[]> params) {
    return zippopotamusClient.validateZipCode(params.get(ORIGIN_PARAM)[0]);
  }
}
