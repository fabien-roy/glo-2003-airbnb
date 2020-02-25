package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.math.BigDecimal;

public class PackageMapper {

  public Package fromRequest(PackageRequest packageRequest) {
    return new Package(
        PackageNames.get(packageRequest.getName()),
        new Price(BigDecimal.valueOf(packageRequest.getPricePerNight())));
  }

  public PackageResponse toResponse(Package bedPackage) {
    return new PackageResponse(
        bedPackage.getName().toString(), bedPackage.getPricePerNight().getValue().doubleValue());
  }
}
