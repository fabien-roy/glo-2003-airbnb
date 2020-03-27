package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.Map;

public class PackageQueryParamAssembler implements BedQueryParamAssembler {

  public static final String PACKAGE_NAME_PARAM = "package";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(PACKAGE_NAME_PARAM) != null
        ? builder.withPackage(parsePackage(params))
        : builder;
  }

  private Packages parsePackage(Map<String, String[]> params) {
    return Packages.get(params.get(PACKAGE_NAME_PARAM)[0]);
  }
}
