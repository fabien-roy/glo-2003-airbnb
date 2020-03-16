package ca.ulaval.glo2003.beds.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public class BedResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String bedNumber;

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
      String bedNumber,
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

  public String getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(String bedNumber) {
    this.bedNumber = bedNumber;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getBedType() {
    return bedType;
  }

  public String getCleaningFrequency() {
    return cleaningFrequency;
  }

  public List<String> getBloodTypes() {
    return bloodTypes;
  }

  public int getCapacity() {
    return capacity;
  }

  public List<PackageResponse> getPackages() {
    return packages;
  }

  public int getStars() {
    return stars;
  }
}
