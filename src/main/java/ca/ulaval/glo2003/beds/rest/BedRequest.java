package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.*;
import ca.ulaval.glo2003.locations.rest.serializers.ZipCodeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;

public class BedRequest {
  private String ownerPublicKey;
  private String zipCode;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes = new ArrayList<>();
  private int capacity = 0;
  private String lodgingMode;
  private List<PackageRequest> packages = new ArrayList<>();

  public String getOwnerPublicKey() {
    return ownerPublicKey;
  }

  @JsonProperty("ownerPublicKey")
  @JsonDeserialize(using = PublicKeyDeserializer.class)
  public void setOwnerPublicKey(String ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
  }

  public String getZipCode() {
    return zipCode;
  }

  @JsonProperty("zipCode")
  @JsonDeserialize(using = ZipCodeDeserializer.class)
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getBedType() {
    return bedType;
  }

  @JsonProperty("bedType")
  @JsonDeserialize(using = BedTypeDeserializer.class)
  public void setBedType(String bedType) {
    this.bedType = bedType;
  }

  public String getCleaningFrequency() {
    return cleaningFrequency;
  }

  @JsonProperty("cleaningFrequency")
  @JsonDeserialize(using = CleaningFrequencyDeserializer.class)
  public void setCleaningFrequency(String cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
  }

  public List<String> getBloodTypes() {
    return bloodTypes;
  }

  @JsonProperty("bloodTypes")
  @JsonDeserialize(using = BloodTypesDeserializer.class)
  public void setBloodTypes(List<String> bloodTypes) {
    if (bloodTypes != null) this.bloodTypes = bloodTypes;
  }

  public int getCapacity() {
    return capacity;
  }

  @JsonProperty("capacity")
  @JsonDeserialize(using = CapacityDeserializer.class)
  public void setCapacity(Integer capacity) {
    if (capacity != null) this.capacity = capacity;
  }

  public String getLodgingMode() {
    return lodgingMode;
  }

  @JsonProperty("lodgingMode")
  public void setLodgingMode(String lodgingMode) {
    this.lodgingMode = lodgingMode;
  }

  public List<PackageRequest> getPackages() {
    return packages;
  }

  @JsonProperty("packages")
  @JsonDeserialize(using = PackagesDeserializer.class)
  public void setPackages(List<PackageRequest> packages) {
    if (packages != null) this.packages = packages;
  }
}
