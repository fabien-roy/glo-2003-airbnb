package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import java.util.List;

public class BedMatcher {

  public static final int UNSET_INT = 0;

  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int minCapacity;
  private Packages packageName;
  private BookingDate arrivalDate;
  private int numberOfNights;
  private LodgingModes lodgingMode;
  private ZipCode origin;
  private int maxDistance;

  public BedMatcher(
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int minCapacity,
      Packages packageName,
      BookingDate arrivalDate,
      int numberOfNights,
      LodgingModes lodgingMode,
      ZipCode origin,
      int maxDistance) {
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.minCapacity = minCapacity;
    this.packageName = packageName;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.lodgingMode = lodgingMode;
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

  public Packages getPackage() {
    return packageName;
  }

  public BookingDate getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(BookingDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public LodgingModes getLodgingMode() {
    return lodgingMode;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public void setNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
  }

  public ZipCode getOrigin() {
    return origin;
  }

  public void setOrigin(ZipCode validZipCode) {
    origin = validZipCode;
  }

  public int getMaxDistance() {
    return maxDistance;
  }

  public void setMaxDistance(int maxDistance) {
    this.maxDistance = maxDistance;
  }

  public boolean matches(Bed bed) {
    if (bedType != null && !bedType.equals(bed.getBedType())) return false;

    if (cleaningFrequency != null && !cleaningFrequency.equals(bed.getCleaningFrequency()))
      return false;

    if (bloodTypes != null && !bed.getBloodTypes().containsAll(bloodTypes)) return false;

    if (minCapacity > bed.getCapacity()) return false;

    if (packageName != null && !bed.isPackageAvailable(packageName)) return false;

    if (arrivalDate != null
        && bed.getBookings().stream()
            .anyMatch(booking -> booking.getDepartureDate().isAfter(arrivalDate.getValue())))
      return false;

    // TODO validate bed availability

    if (lodgingMode != null && !lodgingMode.equals(bed.getLodgingMode())) return false;

    // TODO validate distance between two zipcodes is under max distance

    return true;
  }
}
