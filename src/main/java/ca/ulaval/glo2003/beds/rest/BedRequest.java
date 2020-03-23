package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.locations.domain.Location;
import java.util.List;

public class BedRequest {

  private String ownerPublicKey;
  private Location location;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private String lodgingMode;
  private List<PackageRequest> packages;

  public BedRequest() {
    // Empty constructor for parsing
  }

  public BedRequest(
      String ownerPublicKey,
      Location location,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      String lodgingMode,
      List<PackageRequest> packages) {
    this.ownerPublicKey = ownerPublicKey;
    this.location = location;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.lodgingMode = lodgingMode;
    this.packages = packages;
  }

  public String getOwnerPublicKey() {
    return ownerPublicKey;
  }

  public void setOwnerPublicKey(String ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getBedType() {
    return bedType;
  }

  public void setBedType(String bedType) {
    this.bedType = bedType;
  }

  public String getCleaningFrequency() {
    return cleaningFrequency;
  }

  public void setCleaningFrequency(String cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
  }

  public List<String> getBloodTypes() {
    return bloodTypes;
  }

  public void setBloodTypes(List<String> bloodTypes) {
    this.bloodTypes = bloodTypes;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getLodgingMode() {
    return lodgingMode;
  }

  public List<PackageRequest> getPackages() {
    return packages;
  }

  public void setPackages(List<PackageRequest> packages) {
    this.packages = packages;
  }
}
