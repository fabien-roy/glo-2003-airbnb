package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.locations.domain.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public class BedResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String bedNumber;

  private Location location;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private String lodgingMode;
  private List<PackageResponse> packages;
  private int stars;

  public BedResponse(
      Location location,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      String lodgingMode,
      List<PackageResponse> packages,
      int stars) {
    this.location = location;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.lodgingMode = lodgingMode;
    this.packages = packages;
    this.stars = stars;
  }

  public BedResponse(
      String bedNumber,
      Location location,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      String lodgingMode,
      List<PackageResponse> packages,
      int stars) {
    this.bedNumber = bedNumber;
    this.location = location;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.lodgingMode = lodgingMode;
    this.packages = packages;
    this.stars = stars;
  }

  public String getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(String bedNumber) {
    this.bedNumber = bedNumber;
  }

  public Location getLocation() {
    return location;
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

  public String getLodgingMode() {
    return lodgingMode;
  }

  public List<PackageResponse> getPackages() {
    return packages;
  }

  public int getStars() {
    return stars;
  }
}
