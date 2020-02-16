package ca.ulaval.glo2003.beds.rest;

import java.util.List;

public class BedRequest {

  private String ownerPublicKey;
  private String zipCode;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private List<PackageRequest> packages;

  public String getOwnerPublicKey() {
    return ownerPublicKey;
  }

  public void setOwnerPublicKey(String ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
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

  public List<PackageRequest> getPackages() {
    return packages;
  }

  public void setPackages(List<PackageRequest> packages) {
    this.packages = packages;
  }
}
