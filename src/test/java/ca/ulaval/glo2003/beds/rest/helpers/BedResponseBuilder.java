package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.rest.helpers.BedResponseObjectMother.*;

import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.util.List;

public class BedResponseBuilder {

  private BedResponseBuilder() {}

  private String DEFAULT_BED_NUMBER = createBedNumber();
  private String bedNumber = DEFAULT_BED_NUMBER;

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

  private List<PackageResponse> DEFAULT_PACKAGES = createPackages();
  private List<PackageResponse> packages = DEFAULT_PACKAGES;

  private int DEFAULT_STARS = createStars();
  private int stars = DEFAULT_STARS;

  public static BedResponseBuilder aBedResponse() {
    return new BedResponseBuilder();
  }

  public BedResponseBuilder withStars(int stars) {
    this.stars = stars;
    return this;
  }

  public BedResponse build() {
    return new BedResponse(
        bedNumber,
        zipCode,
        bedType,
        cleaningFrequency,
        bloodTypes,
        capacity,
        lodgingMode,
        packages,
        stars);
  }
}
