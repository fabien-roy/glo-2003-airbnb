package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.ExceedingAccommodationCapacityException;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed) {
    validateCapacity(bed);
    validatePackageDependencies(bed);
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    return bed;
  }

  private void validateCapacity(Bed bed) {
    int maxCapacity = BedTypesCapacities.get(bed.getBedType());

    if (bed.getCapacity() > maxCapacity) {
      throw new ExceedingAccommodationCapacityException();
    }
  }

  private void validatePackageDependencies(Bed bed) {
    for (Packages testPackage : Packages.values()) {
      if (bed.isPackageAvailable(testPackage)
          && !bed.isPackageAvailable(PackagesDependency.getDependency(testPackage))) {
        throw PackagesDependency.getException(testPackage);
      }
    }
  }
}
