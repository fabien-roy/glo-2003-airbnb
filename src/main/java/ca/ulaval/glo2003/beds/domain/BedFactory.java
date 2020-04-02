package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.locations.domain.Location;
import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed, Location location) {
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    bed.setLocation(location);
    return bed;
  }
}
