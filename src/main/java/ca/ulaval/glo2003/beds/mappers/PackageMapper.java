package ca.ulaval.glo2003.beds.mappers;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import javax.inject.Inject;

public class PackageMapper {

  private PriceMapper priceMapper;

  @Inject
  public PackageMapper(PriceMapper priceMapper) {
    this.priceMapper = priceMapper;
  }

  public Map<Packages, Price> fromRequests(List<PackageRequest> packageRequests) {
    validateRequests(packageRequests);
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    packageRequests.forEach(
        packageRequest -> {
          Packages packageName = Packages.get(packageRequest.getName());
          Price price = priceMapper.fromDouble(packageRequest.getPricePerNight());
          pricesPerNight.put(packageName, price);
        });
    validatePackageOnce(pricesPerNight.keySet(), packageRequests);
    return pricesPerNight;
  }

  public List<PackageResponse> toResponses(Map<Packages, Price> pricesPerNight) {
    List<PackageResponse> packageResponses = new ArrayList<>();

    pricesPerNight.forEach(
        (packageName, price) -> {
          Double priceValue = priceMapper.toDouble(price);
          packageResponses.add(new PackageResponse(packageName.toString(), priceValue));
        });

    return packageResponses;
  }

  private void validateRequests(List<PackageRequest> packageRequests) {
    if (packageRequests == null || packageRequests.isEmpty()) throw new InvalidPackageException();
  }

  private void validatePackageOnce(
      Set<Packages> packageRequestsSet, List<PackageRequest> packageRequestsList) {
    if (packageRequestsSet.size() != packageRequestsList.size()) {
      throw new InvalidPackageException();
    }
  }
}
