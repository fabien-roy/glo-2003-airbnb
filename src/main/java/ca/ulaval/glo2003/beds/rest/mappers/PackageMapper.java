package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PackageMapper {

  public Map<Packages, Price> fromRequests(List<PackageRequest> packageRequests) {
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    packageRequests.forEach(
        packageRequest -> {
          Packages packageName = Packages.get(packageRequest.getName());
          Price price = new Price(BigDecimal.valueOf(packageRequest.getPricePerNight()));
          pricesPerNight.put(packageName, price);
        });
    validatePackageOnce(pricesPerNight.keySet(), packageRequests);
    return pricesPerNight;
  }

  public List<PackageResponse> toResponses(Map<Packages, Price> pricesPerNight) {
    List<PackageResponse> packageResponses = new ArrayList<>();

    pricesPerNight.forEach(
        (packageName, price) ->
            packageResponses.add(
                new PackageResponse(packageName.toString(), price.getValue().doubleValue())));

    return packageResponses;
  }

  private void validatePackageOnce(
      Set<Packages> packageRequestsSet, List<PackageRequest> packageRequestsList) {
    if (packageRequestsSet.size() != packageRequestsList.size()) {
      throw new InvalidPackageException();
    }
  }
}
