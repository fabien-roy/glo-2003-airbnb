package ca.ulaval.glo2003.beds.domain.helpers;

import ca.ulaval.glo2003.beds.domain.*;
import java.util.List;

public class BedMatcherBuilder {

  private BedMatcherBuilder() {}

  private BedTypes bedType = null;
  private CleaningFrequencies cleaningFrequency = null;
  private List<BloodTypes> bloodTypes = null;
  private int capacity = 0;
  private PackageNames packageName = null;

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

  public BedMatcherBuilder withCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public BedMatcherBuilder withPackageName(PackageNames packageName) {
    this.packageName = packageName;
    return this;
  }

  public BedMatcher build() {
    return new BedMatcher(bedType, cleaningFrequency, bloodTypes, capacity, packageName);
  }
}
