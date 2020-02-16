package ca.ulaval.glo2003.beds.domain;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Bed {

  private UUID number;
  private Base64 ownerPublicKey;
  private String zipCode; // TODO : We might want to keep zipCode in a separated class
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private List<Package> packages;

  public UUID getNumber() {
    return number;
  }
}
