package ca.ulaval.glo2003.beds.domain;

import java.util.UUID;

public class BedFactory {

  public Bed create(Bed bed) {
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    return bed;
  }
}
