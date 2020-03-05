package ca.ulaval.glo2003.beds.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.UUID;

public class BedResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private UUID bedNumber;

  private String zipCode;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private List<PackageResponse> packages;
  private int stars;

  public BedResponse(
      String zipCode,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      List<PackageResponse> packages,
      int stars) {
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.packages = packages;
    this.stars = stars;
  }

  public BedResponse(
      UUID bedNumber,
      String zipCode,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      List<PackageResponse> packages,
      int stars) {
    this.bedNumber = bedNumber;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.packages = packages;
    this.stars = stars;
  }

  public UUID getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(UUID bedNumber) {
    this.bedNumber = bedNumber;
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

  public List<PackageResponse> getPackages() {
    return packages;
  }

  public void setPackages(List<PackageResponse> packages) {
    this.packages = packages;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }
}
