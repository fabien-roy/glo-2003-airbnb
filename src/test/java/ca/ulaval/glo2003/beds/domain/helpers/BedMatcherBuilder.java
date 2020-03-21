package ca.ulaval.glo2003.beds.domain.helpers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import java.util.List;

public class BedMatcherBuilder {

  private BedMatcherBuilder() {}

  private BedTypes bedType = null;
  private CleaningFrequencies cleaningFrequency = null;
  private List<BloodTypes> bloodTypes = null;
  private int minCapacity = 0;
  private Packages packageName = null;
  private int maxDistance = 0;
  private ZipCode origin = null;
  private LodgingModes lodgingMode = null;
  private BookingDate arrivalDate = null;

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

  public BedMatcher build() {
    return new BedMatcher(
        bedType,
        cleaningFrequency,
        bloodTypes,
        minCapacity,
        packageName,
        maxDistance,
        origin,
        lodgingMode,
        arrivalDate);
  }
}
