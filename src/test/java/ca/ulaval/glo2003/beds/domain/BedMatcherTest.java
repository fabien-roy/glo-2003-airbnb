package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.helpers.BedMatcherBuilder.aBedMatcher;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BedMatcherTest {

  @Test
  public void matches_withEmptyBed_shouldReturnTrue() {
    BedMatcher bedMatcher = aBedMatcher().build();
    Bed bed = aBed().build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameBedType_shouldReturnTrue() {
    BedTypes bedType = BedTypes.LATEX;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).build();
    Bed bed = aBed().withBedType(bedType).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentBedType_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedType = BedTypes.MEMORY_FOAM;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).build();
    Bed bed = aBed().withBedType(otherBedType).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCleaningFrequency_shouldReturnTrue() {
    CleaningFrequencies cleaningFrequencies = CleaningFrequencies.ANNUAL;
    BedMatcher bedMatcher = aBedMatcher().withCleaningFrequency(cleaningFrequencies).build();
    Bed bed = aBed().withCleaningFrequency(cleaningFrequencies).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentCleaningFrequency_shouldReturnFalse() {
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;
    BedMatcher bedMatcher = aBedMatcher().withCleaningFrequency(cleaningFrequency).build();
    Bed bed = aBed().withCleaningFrequency(otherCleaningFrequency).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSinglePresentBloodType_shouldReturnTrue() {
    List<BloodTypes> requestedBloodTypes = Collections.singletonList(BloodTypes.O_MINUS);
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withMultiplePresentBloodTypes_shouldReturnTrue() {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.AB_MINUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSingleNotPresentBloodType_shouldReturnFalse() {
    List<BloodTypes> requestedBloodTypes = Collections.singletonList(BloodTypes.AB_MINUS);
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSingleNotPresentBloodTypeInMultipleBloodTypes_shouldReturnFalse() {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.AB_MINUS, BloodTypes.A_MINUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.A_MINUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withMultipleNotPresentBloodTypes_shouldReturnFalse() {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.AB_MINUS, BloodTypes.A_MINUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.B_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCapacity_shouldReturnTrue() {
    int capacity = 100;
    BedMatcher bedMatcher = aBedMatcher().withCapacity(capacity).build();
    Bed bed = aBed().withCapacity(capacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentCapacity_shouldReturnFalse() {
    int capacity = 100;
    int otherCapacity = 200;
    BedMatcher bedMatcher = aBedMatcher().withCapacity(capacity).build();
    Bed bed = aBed().withCapacity(otherCapacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameSinglePackage_shouldReturnTrue() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    List<Package> packages = Collections.singletonList(bedPackage);
    BedMatcher bedMatcher = aBedMatcher().withPackages(packages).build();
    Bed bed = aBed().withPackages(packages).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameMultiplePackages_shouldReturnTrue() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    List<Package> packages = Arrays.asList(bedPackage, otherBedPackage);
    BedMatcher bedMatcher = aBedMatcher().withPackages(packages).build();
    Bed bed = aBed().withPackages(packages).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentPackage_shouldReturnFalse() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    List<Package> packages = Collections.singletonList(bedPackage);
    List<Package> otherPackages = Collections.singletonList(otherBedPackage);
    BedMatcher bedMatcher = aBedMatcher().withPackages(packages).build();
    Bed bed = aBed().withPackages(otherPackages).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withMultipleDifferentPackages_shouldReturnFalse() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    Package anotherBedPackage =
        new Package(PackageNames.ALL_YOU_CAN_DRINK, BigDecimal.valueOf(300));
    List<Package> packages = Arrays.asList(bedPackage, otherBedPackage);
    List<Package> otherPackages = Arrays.asList(otherBedPackage, anotherBedPackage);
    BedMatcher bedMatcher = aBedMatcher().withPackages(packages).build();
    Bed bed = aBed().withPackages(otherPackages).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withDifferentPackagesAmount_shouldReturnFalse() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    Package anotherBedPackage =
        new Package(PackageNames.ALL_YOU_CAN_DRINK, BigDecimal.valueOf(300));
    List<Package> packages = Arrays.asList(bedPackage, otherBedPackage, anotherBedPackage);
    List<Package> otherPackages = Arrays.asList(bedPackage, otherBedPackage);
    BedMatcher bedMatcher = aBedMatcher().withPackages(packages).build();
    Bed bed = aBed().withPackages(otherPackages).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllSameAttributes_shouldReturnTrue() {
    BedTypes bedType = BedTypes.LATEX;
    int capacity = 100;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).withCapacity(capacity).build();
    Bed bed = aBed().withBedType(bedType).withCapacity(capacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentAttribute_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    int capacity = 100;
    int otherCapacity = 200;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).withCapacity(capacity).build();
    Bed bed = aBed().withBedType(bedType).withCapacity(otherCapacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllDifferentAttributes_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedtype = BedTypes.MEMORY_FOAM;
    int capacity = 100;
    int otherCapacity = 200;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).withCapacity(capacity).build();
    Bed bed = aBed().withBedType(otherBedtype).withCapacity(otherCapacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }
}
