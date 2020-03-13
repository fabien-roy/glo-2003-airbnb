package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestObjectMother.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import java.util.List;

public class BedRequestBuilder {

  private BedRequestBuilder() {}

  private String DEFAULT_OWNER_PUBLIC_KEY = createOwnerPublicKey();
  private String ownerPublicKey = DEFAULT_OWNER_PUBLIC_KEY;

  private String DEFAULT_ZIP_CODE = createZipCode();
  private String zipCode = DEFAULT_ZIP_CODE;

  private String DEFAULT_BED_TYPE = createBedType();
  private String bedType = DEFAULT_BED_TYPE;

  private String DEFAULT_CLEANING_FREQUENCY = createCleaningFrequency();
  private String cleaningFrequency = DEFAULT_CLEANING_FREQUENCY;

  private List<String> DEFAULT_BLOOD_TYPES = createBloodTypes();
  private List<String> bloodTypes = DEFAULT_BLOOD_TYPES;

  private int DEFAULT_CAPACITY = createCapacity();
  private int capacity = DEFAULT_CAPACITY;

  private String DEFAULT_LODGING_MODE = createLodgingMode();
  private String lodgingMode = DEFAULT_LODGING_MODE;

  private List<PackageRequest> DEFAULT_PACKAGES = createPackages();
  private List<PackageRequest> packages = DEFAULT_PACKAGES;

  public static BedRequestBuilder aBedRequest() {
    return new BedRequestBuilder();
  }

  public BedRequestBuilder withOwnerPublicKey(String ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
    return this;
  }

  public BedRequestBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public BedRequestBuilder withBedType(String bedType) {
    this.bedType = bedType;
    return this;
  }

  public BedRequestBuilder withCleaningFrequency(String cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
    return this;
  }

  public BedRequestBuilder withBloodTypes(List<String> bloodTypes) {
    this.bloodTypes = bloodTypes;
    return this;
  }

  public BedRequestBuilder withCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public BedRequestBuilder withLodgingMode(String lodgingMode) {
    this.lodgingMode = lodgingMode;
    return this;
  }

  public BedRequestBuilder withPackages(List<PackageRequest> packages) {
    this.packages = packages;
    return this;
  }

  public BedRequest build() {
    return new BedRequest(
        ownerPublicKey,
        zipCode,
        bedType,
        cleaningFrequency,
        bloodTypes,
        capacity,
        lodgingMode,
        packages);
  }
}
