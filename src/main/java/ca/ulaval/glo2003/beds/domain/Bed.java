package ca.ulaval.glo2003.beds.domain;

import java.util.List;
import java.util.UUID;

public class Bed {

  private UUID number;
  private String ownerPublicKey;
  private String zipCode; // TODO : We might want to keep zipCode in a separated class
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private List<Package> packages;

  public Bed() {
    // TODO : Delete Bed constructor without param
  }

  public Bed(
      UUID number,
      String ownerPublicKey,
      String zipCode,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      List<Package> packages) {
    this.number = number;
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.packages = packages;
  }

  public UUID getNumber() {
    return number;
  }

  public BedTypes getBedType() {
    return bedType;
  }

  public void setBedType(BedTypes bedType) {
    this.bedType = bedType;
  }

  public CleaningFrequencies getCleaningFrequency() {
    return cleaningFrequency;
  }

  public List<BloodTypes> getBloodTypes() {
    return bloodTypes;
  }

  public int getCapacity() {
    return capacity;
  }

  public List<Package> getPackages() {
    return packages;
  }

  // This is different than equals : we check if beds match on specific non-null attributes rather
  // than all attributes
  public boolean matches(Bed otherBed) {
    if (otherBed.getBedType() != null && !bedType.equals(otherBed.getBedType())) return false;

    if (otherBed.getCleaningFrequency() != null
        && !cleaningFrequency.equals(otherBed.getCleaningFrequency())) return false;

    if (otherBed.getBloodTypes() != null && !bloodTypes.equals(otherBed.getBloodTypes()))
      return false;

    if (otherBed.getCapacity() > 0 && capacity != otherBed.getCapacity()) return false;

    if (otherBed.getPackages() != null && !packages.equals(otherBed.getPackages())) return false;

    return true;
  }
}
