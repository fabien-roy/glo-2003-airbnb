package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.locations.domain.Location;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed, Location location) {
    validateCapacity(bed);
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
}
