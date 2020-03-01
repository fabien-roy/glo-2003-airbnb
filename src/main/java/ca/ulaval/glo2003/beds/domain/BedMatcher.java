package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public class BedMatcher {

  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int minCapacity;
  private Packages packageName;

  public BedMatcher(
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int minCapacity,
      Packages packageName) {
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.minCapacity = minCapacity;
    this.packageName = packageName;
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

  public boolean matches(Bed bed) {
    if (bedType != null && !bedType.equals(bed.getBedType())) return false;

    if (cleaningFrequency != null && !cleaningFrequency.equals(bed.getCleaningFrequency()))
      return false;

    if (bloodTypes != null && !bed.getBloodTypes().containsAll(bloodTypes)) return false;

    if (minCapacity > bed.getCapacity()) return false;

    if (packageName != null && !bed.isPackageAvailable(packageName)) return false;

    return true;
  }
}
