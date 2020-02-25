package ca.ulaval.glo2003.beds.domain;

import java.util.UUID;

public class BedFactory {

  // TODO : Use BedTypesCapacities to make sure ExceedingCapacityException should not be thrown
  public Bed create(Bed bed) {
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    return bed;
  }
}
