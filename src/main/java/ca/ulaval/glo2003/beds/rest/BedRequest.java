package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.*;
import ca.ulaval.glo2003.locations.rest.serializers.ZipCodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.Arrays;
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
  private int capacity = 0;

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
      String[] bloodTypes,
      Integer capacity,
      String lodgingMode,
      PackageRequest[] packages) {
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;

    if (bloodTypes != null) this.bloodTypes = Arrays.asList(bloodTypes);

    if (capacity != null) this.capacity = capacity;

    this.lodgingMode = lodgingMode;

    if (packages != null) this.packages = Arrays.asList(packages);
  }

  public String getOwnerPublicKey() {
    return ownerPublicKey;
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

  public String getLodgingMode() {
    return lodgingMode;
  }

  public List<PackageRequest> getPackages() {
    return packages;
  }
}
