package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.BedMatcher.UNSET_INT;

import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import java.util.List;

public class BedMatcherBuilder {

  private BedMatcherBuilder() {}

  private BedTypes bedType = null;
  private CleaningFrequencies cleaningFrequency = null;
  private List<BloodTypes> bloodTypes = null;
  private int minCapacity = UNSET_INT;
  private Packages packageName = null;
  private BookingDate arrivalDate = null;
  private int maxDistance = UNSET_INT;
  private int numberOfNights = UNSET_INT;
  private ZipCode origin = null;
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

  public BedMatcherBuilder withArrivalDate(BookingDate arrivalDate) {
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

  public BedMatcherBuilder withOrigin(ZipCode origin) {
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
