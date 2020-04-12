package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeObjectMother.createLodgingModeName;
import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;

import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BedTypesCapacities;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BedRequestBuilder {

  private BedRequestBuilder() {}

  private String DEFAULT_OWNER_PUBLIC_KEY = createPublicKey().toString();
  private String ownerPublicKey = DEFAULT_OWNER_PUBLIC_KEY;

  private String DEFAULT_ZIP_CODE = createZipCode().toString();
  private String zipCode = DEFAULT_ZIP_CODE;

  private String DEFAULT_BED_TYPE = createBedType().toString();
  private String bedType = DEFAULT_BED_TYPE;

  private String DEFAULT_CLEANING_FREQUENCY = createCleaningFrequency().toString();
  private String cleaningFrequency = DEFAULT_CLEANING_FREQUENCY;

  private List<String> DEFAULT_BLOOD_TYPES =
      createBloodTypes().stream().map(BloodTypes::toString).collect(Collectors.toList());
  private List<String> bloodTypes = DEFAULT_BLOOD_TYPES;

  private int DEFAULT_CAPACITY = BedTypesCapacities.get(BedTypes.get(DEFAULT_BED_TYPE));
  private int capacity = DEFAULT_CAPACITY;

  private String DEFAULT_LODGING_MODE = createLodgingModeName().toString();
  private String lodgingMode = DEFAULT_LODGING_MODE;

  private List<PackageRequest> DEFAULT_PACKAGES =
      Collections.singletonList(aPackageRequest().build());
  private List<PackageRequest> packages = DEFAULT_PACKAGES;

  public static BedRequestBuilder aBedRequest() {
    return new BedRequestBuilder();
  }

  public BedRequestBuilder withOwnerPublicKey(String ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
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

  public BedRequest build() {
    BedRequest bedRequest = new BedRequest();
    bedRequest.setOwnerPublicKey(ownerPublicKey);
    bedRequest.setZipCode(zipCode);
    bedRequest.setBedType(bedType);
    bedRequest.setCleaningFrequency(cleaningFrequency);
    bedRequest.setBloodTypes(bloodTypes);
    bedRequest.setCapacity(capacity);
    bedRequest.setLodgingMode(lodgingMode);
    bedRequest.setPackages(packages);
    return bedRequest;
  }
}
