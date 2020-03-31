package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.converters.validators.PackageValidator;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class PackageConverter {

  private final PriceConverter priceConverter;
  private final Set<PackageValidator> validators;
  private final PackageValidator defaultValidator;

  @Inject
  public PackageConverter(
      PriceConverter priceConverter,
      Set<PackageValidator> validators,
      PackageValidator defaultValidator) {
    this.priceConverter = priceConverter;
    this.validators = validators;
    this.defaultValidator = defaultValidator;
  }

  public Map<Packages, Price> fromRequests(List<PackageRequest> packageRequests) {
    validateRequests(packageRequests);
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);

    packageRequests.forEach(
        packageRequest -> {
          Packages packageName = parsePackageName(packageRequest.getName());
          Price price = priceConverter.fromDouble(packageRequest.getPricePerNight());
          pricesPerNight.put(packageName, price);
        });

    validatePackageOnce(pricesPerNight.keySet(), packageRequests);
    return pricesPerNight;
  }

  public List<PackageResponse> toResponses(Map<Packages, Price> pricesPerNight) {
    List<PackageResponse> packageResponses = new ArrayList<>();

    pricesPerNight.forEach(
        (packageName, price) -> {
          Double priceValue = priceConverter.toDouble(price);
          packageResponses.add(new PackageResponse(packageName.toString(), priceValue));
        });

    return packageResponses;
  }

  private void validateRequests(List<PackageRequest> requests) {
    if (requests.isEmpty()) throw new InvalidPackagesException();

    if (requests.stream().anyMatch(request -> request.getPricePerNight() <= 0))
      throw new InvalidPackagesException();

    validatePackagesDependencies(requests);
  }

  private Packages parsePackageName(String packageName) {
    try {
      return Packages.get(packageName);
    } catch (InvalidPackageException e) {
      throw new InvalidPackagesException();
    }
  }

  private void validatePackageOnce(
      Set<Packages> packageRequestsSet, List<PackageRequest> packageRequestsList) {
    if (packageRequestsSet.size() != packageRequestsList.size()) {
      throw new InvalidPackagesException();
    }
  }

  private void validatePackagesDependencies(List<PackageRequest> requests) {
    Set<Packages> presentPackages =
        requests.stream()
            .map(packageRequest -> parsePackageName(packageRequest.toString()))
            .collect(Collectors.toSet());

    presentPackages.forEach(
        packageName -> validatePackageDependencies(packageName, presentPackages));
  }

  private void validatePackageDependencies(Packages packageName, Set<Packages> presentPackages) {
    PackageValidator foundValidator = findValidator(packageName);
    List<Packages> dependencies = foundValidator.get();
    if (!presentPackages.containsAll(dependencies)) foundValidator.throwException();
  }

  private PackageValidator findValidator(Packages packages) {
    Optional<PackageValidator> packageValidator =
        validators.stream().filter(validator -> validator.isPackage(packages)).findFirst();
    return packageValidator.orElse(defaultValidator);
  }
}
