package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.*;
import ca.ulaval.glo2003.locations.rest.serializers.ZipCodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;

public class BedRequest {

  @JsonDeserialize(using = PublicKeyDeserializer.class)
  private String ownerPublicKey;

  @JsonDeserialize(using = ZipCodeDeserializer.class)
  private String zipCode;

  @JsonDeserialize(using = BedTypeDeserializer.class)
  private String bedType;

  @JsonDeserialize(using = CleaningFrequencyDeserializer.class)
  private String cleaningFrequency;

  @JsonDeserialize(using = BloodTypesDeserializer.class)
  private List<String> bloodTypes = new ArrayList<>();

  @JsonDeserialize(using = CapacityDeserializer.class)
  private int capacity;

  private String lodgingMode;

  @JsonDeserialize(using = PackagesDeserializer.class)
  private List<PackageRequest> packages = new ArrayList<>();

  public BedRequest() {
    // Empty constructor for parsing
  }

  public BedRequest(
      String ownerPublicKey,
      String zipCode,
      String bedType,
      String cleaningFrequency,
      List<String> bloodTypes,
      int capacity,
      String lodgingMode,
      List<PackageRequest> packages) {
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
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
    if (bloodTypes != null) this.bloodTypes = bloodTypes;
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
    if (packages != null) this.packages = packages;
  }
}
