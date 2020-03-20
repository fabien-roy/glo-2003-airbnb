package ca.ulaval.glo2003.beds.domain.helpers;

import ca.ulaval.glo2003.beds.domain.*;
import java.time.LocalDate;
import java.util.List;

public class BedMatcherBuilder {

  private BedMatcherBuilder() {}

  private BedTypes bedType = null;
  private CleaningFrequencies cleaningFrequency = null;
  private List<BloodTypes> bloodTypes = null;
  private int minCapacity = 0;
  private Packages packageName = null;
  private LocalDate arrivalDate = null;
  private int maxDistance = 0;
  private int numberOfNights = 0;
  private String origin = null;
  private LodgingModes lodgingMode = null;

  public static BedMatcherBuilder aBedMatcher() {
    return new BedMatcherBuilder();
  }

  public BedMatcherBuilder withBedType(BedTypes bedType) {
    this.bedType = bedType;
    return this;
  }

  public BedMatcherBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
    return this;
  }

  public BedMatcherBuilder withBloodTypes(List<BloodTypes> bloodTypes) {
    this.bloodTypes = bloodTypes;
    return this;
  }

  public BedMatcherBuilder withMinCapacity(int capacity) {
    this.minCapacity = capacity;
    return this;
  }

  public BedMatcherBuilder withPackageName(Packages packageName) {
    this.packageName = packageName;
    return this;
  }

  public BedMatcherBuilder withArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  public BedMatcherBuilder withMaxDistance(int maxDistance) {
    this.maxDistance = maxDistance;
    return this;
  }

  public BedMatcherBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  public BedMatcherBuilder withOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public BedMatcherBuilder withLodging(LodgingModes lodgingMode) {
    this.lodgingMode = lodgingMode;
    return this;
  }

  public BedMatcher build() {
    return new BedMatcher(
        bedType,
        cleaningFrequency,
        bloodTypes,
        minCapacity,
        packageName,
        arrivalDate,
        numberOfNights,
        lodgingMode,
        origin,
        maxDistance);
  }
}
