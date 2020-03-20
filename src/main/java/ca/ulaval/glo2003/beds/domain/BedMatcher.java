package ca.ulaval.glo2003.beds.domain;

import java.time.LocalDate;
import java.util.List;

public class BedMatcher {

  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int minCapacity;
  private Packages packageName;
  private LocalDate arrivalDate;
  private int numberOfNights;
  private LodgingModes lodgingModes;
  private String origin;
  private int maxDistance;

  public BedMatcher(
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int minCapacity,
      Packages packageName,
      LocalDate arrivalDate,
      int numberOfNights,
      LodgingModes lodgingModes,
      String origin,
      int maxDistance) {
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.minCapacity = minCapacity;
    this.packageName = packageName;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.lodgingModes = lodgingModes;
    this.origin = origin;
    this.maxDistance = maxDistance;
  }

  public BedTypes getBedType() {
    return bedType;
  }

  public CleaningFrequencies getCleaningFrequency() {
    return cleaningFrequency;
  }

  public List<BloodTypes> getBloodTypes() {
    return bloodTypes;
  }

  public int getMinCapacity() {
    return minCapacity;
  }

  public Packages getPackageName() {
    return packageName;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public LodgingModes getLodgingModes() {
    return lodgingModes;
  }

  public String getOrigin() {
    return origin;
  }

  public int getMaxDistance() {
    return maxDistance;
  }

  public boolean matches(Bed bed) {
    if (bedType != null && !bedType.equals(bed.getBedType())) return false;

    if (cleaningFrequency != null && !cleaningFrequency.equals(bed.getCleaningFrequency()))
      return false;

    if (bloodTypes != null && !bed.getBloodTypes().containsAll(bloodTypes)) return false;

    if (minCapacity > bed.getCapacity()) return false;

    if (packageName != null && !bed.isPackageAvailable(packageName)) return false;

    if (arrivalDate != null) {
      if (bed.getBookings().stream()
          .anyMatch(booking -> booking.getDepartureDate().isAfter(arrivalDate))) return false;
    }

    // TODO validate bed availability

    if (lodgingModes != null && !lodgingModes.equals(bed.getLodgingMode())) return false;

    if (origin != null && !origin.contains(bed.getZipCode().getValue())) return false;

    // TODO validate distance between two zipcodes is under max distance

    return true;
  }
}
