package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import java.util.Set;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed, ZipCode zipCode) {
    validateCapacity(bed);
    validatePackageDependencies(bed);
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    bed.setZipCode(zipCode);
    return bed;
  }

  private void validateCapacity(Bed bed) {
    int maxCapacity = BedTypesCapacities.get(bed.getBedType());

    if (bed.getCapacity() > maxCapacity) {
      throw new ExceedingAccommodationCapacityException();
    }
  }

  private void validatePackageDependencies(Bed bed) {
    Set<Packages> packages = bed.getPackages();
    packages.forEach(packageName -> validatePackageDependency(packageName, packages));
  }

  private void validatePackageDependency(Packages packageName, Set<Packages> packages) {
    Packages dependency = PackagesDependency.getDependency(packageName);

    if (dependency != null && !packages.contains(dependency)) {
      throw PackagesDependency.getException(packageName);
    }
  }
}
