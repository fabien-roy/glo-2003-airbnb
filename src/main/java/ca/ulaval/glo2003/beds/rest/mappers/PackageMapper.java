package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.PackageResponse;

public class PackageMapper {

  public PackageResponse toResponse(Package bedPackage) {
    return new PackageResponse(
        bedPackage.getName().toString(), bedPackage.getPricePerNight().doubleValue());
  }
}
