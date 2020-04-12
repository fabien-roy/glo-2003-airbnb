package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.locations.domain.Location;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed, Location location) {
    bed.setNumber(createNumber());
    bed.setLocation(location);
    return bed;
  }

  private BedNumber createNumber() {
    return new BedNumber(UUID.randomUUID());
  }
}
