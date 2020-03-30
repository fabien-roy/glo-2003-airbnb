package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.locations.domain.Location;
import java.util.Set;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed, Location location) {
    validateCapacity(bed);
    OutdatedvalidatePackageDependencies(bed);
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    bed.setLocation(location);
    return bed;
  }

  private void validateCapacity(Bed bed) {
    int maxCapacity = BedTypesCapacities.get(bed.getBedType());

    if (bed.getCapacity() > maxCapacity) {
      throw new ExceedingAccommodationCapacityException();
    }
  }

  private void OutdatedvalidatePackageDependencies(Bed bed) {
    Set<Packages> packages = bed.getPackages();
    packages.forEach(packageName -> outdatedvalidatePackageDependency(packageName, packages));
  }

  private void outdatedvalidatePackageDependency(Packages packageName, Set<Packages> packages) {
    Packages dependency = OutdatedPackagesDependencies.getDependency(packageName);

    if (dependency != null && !packages.contains(dependency)) {
      throw OutdatedPackagesDependencies.getException(packageName);
    }
  }
}
