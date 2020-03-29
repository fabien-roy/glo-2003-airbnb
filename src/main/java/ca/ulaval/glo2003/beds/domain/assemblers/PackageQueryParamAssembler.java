package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;
import java.util.Map;

public class PackageQueryParamAssembler implements BedQueryParamAssembler {

  public static final String PACKAGE_NAME_PARAM = "package";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> packages = params.get(PACKAGE_NAME_PARAM);

    return packages != null ? builder.withPackage(Packages.get(packages.get(0))) : builder;
  }
}
