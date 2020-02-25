package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PackageMapper {

  // TODO : Remove
  public Package fromRequest(PackageRequest packageRequest) {
    return new Package(
        PackageNames.get(packageRequest.getName()),
        new Price(BigDecimal.valueOf(packageRequest.getPricePerNight())));
  }

  public Map<PackageNames, Price> fromRequests(List<PackageRequest> packageRequests) {
    Map<PackageNames, Price> pricesPerNight = new EnumMap<>(PackageNames.class);

    packageRequests.forEach(
        packageRequest -> {
          PackageNames packageName = PackageNames.get(packageRequest.getName());
          Price price = new Price(BigDecimal.valueOf(packageRequest.getPricePerNight()));
          pricesPerNight.put(packageName, price);
        });

    return pricesPerNight;
  }

  // TODO : Remove
  public PackageResponse toResponse(Package bedPackage) {
    return new PackageResponse(
        bedPackage.getName().toString(), bedPackage.getPricePerNight().getValue().doubleValue());
  }

  public List<PackageResponse> toResponses(Map<PackageNames, Price> pricesPerNight) {
    List<PackageResponse> packageResponses = new ArrayList<>();

    pricesPerNight.forEach(
        (packageName, price) ->
            packageResponses.add(
                new PackageResponse(packageName.toString(), price.getValue().doubleValue())));

    return packageResponses;
  }
}
