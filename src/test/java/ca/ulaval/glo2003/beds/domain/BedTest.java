package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  public void matches_withEmptyBed_shouldReturnTrue() {
    Bed bed = aBed().build();
    Bed otherBed = aBed().withNullAttributes().build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameBedType_shouldReturnTrue() {
    BedTypes bedType = BedTypes.LATEX;
    Bed bed = aBed().withBedType(bedType).build();
    Bed otherBed = aBed().withNullAttributes().withBedType(bedType).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentBedType_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedType = BedTypes.MEMORY_FOAM;
    Bed bed = aBed().withBedType(bedType).build();
    Bed otherBed = aBed().withNullAttributes().withBedType(otherBedType).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCleaningFrequency_shouldReturnTrue() {
    CleaningFrequencies cleaningFrequencies = CleaningFrequencies.ANNUAL;
    Bed bed = aBed().withCleaningFrequency(cleaningFrequencies).build();
    Bed otherBed = aBed().withNullAttributes().withCleaningFrequency(cleaningFrequencies).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentCleaningFrequency_shouldReturnFalse() {
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;
    Bed bed = aBed().withCleaningFrequency(cleaningFrequency).build();
    Bed otherBed =
        aBed().withNullAttributes().withCleaningFrequency(otherCleaningFrequency).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameSingleBloodType_shouldReturnTrue() {
    List<BloodTypes> bloodTypes = Collections.singletonList(BloodTypes.O_MINUS);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();
    Bed otherBed = aBed().withNullAttributes().withBloodTypes(bloodTypes).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameMultipleBloodTypes_shouldReturnTrue() {
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();
    Bed otherBed = aBed().withNullAttributes().withBloodTypes(bloodTypes).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentBloodType_shouldReturnFalse() {
    List<BloodTypes> bloodTypes = Collections.singletonList(BloodTypes.O_MINUS);
    List<BloodTypes> otherBloodTypes = Collections.singletonList(BloodTypes.O_PLUS);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();
    Bed otherBed = aBed().withNullAttributes().withBloodTypes(otherBloodTypes).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withMultipleDifferentBloodTypes_shouldReturnFalse() {
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.AB_MINUS);
    List<BloodTypes> otherBloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.A_MINUS, BloodTypes.B_MINUS);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();
    Bed otherBed = aBed().withNullAttributes().withBloodTypes(otherBloodTypes).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withDifferentBloodTypesAmount_shouldReturnFalse() {
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.AB_MINUS);
    List<BloodTypes> otherBloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();
    Bed otherBed = aBed().withNullAttributes().withBloodTypes(otherBloodTypes).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCapacity_shouldReturnTrue() {
    int capacity = 100;
    Bed bed = aBed().withCapacity(capacity).build();
    Bed otherBed = aBed().withNullAttributes().withCapacity(capacity).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentCapacity_shouldReturnFalse() {
    int capacity = 100;
    int otherCapacity = 200;
    Bed bed = aBed().withCapacity(capacity).build();
    Bed otherBed = aBed().withNullAttributes().withCapacity(otherCapacity).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameSinglePackage_shouldReturnTrue() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    List<Package> packages = Collections.singletonList(bedPackage);
    Bed bed = aBed().withPackages(packages).build();
    Bed otherBed = aBed().withNullAttributes().withPackages(packages).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameMultiplePackages_shouldReturnTrue() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    List<Package> packages = Arrays.asList(bedPackage, otherBedPackage);
    Bed bed = aBed().withPackages(packages).build();
    Bed otherBed = aBed().withNullAttributes().withPackages(packages).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentPackage_shouldReturnFalse() {
    Package bedPackage = new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(100));
    Package otherBedPackage = new Package(PackageNames.SWEET_TOOTH, BigDecimal.valueOf(200));
    List<Package> packages = Collections.singletonList(bedPackage);
    List<Package> otherPackages = Collections.singletonList(otherBedPackage);
    Bed bed = aBed().withPackages(packages).build();
    Bed otherBed = aBed().withNullAttributes().withPackages(otherPackages).build();

    boolean matches = bed.matches(otherBed);

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
    Bed bed = aBed().withPackages(packages).build();
    Bed otherBed = aBed().withNullAttributes().withPackages(otherPackages).build();

    boolean matches = bed.matches(otherBed);

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
    Bed bed = aBed().withPackages(packages).build();
    Bed otherBed = aBed().withNullAttributes().withPackages(otherPackages).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllSameAttributes_shouldReturnTrue() {
    BedTypes bedType = BedTypes.LATEX;
    int capacity = 100;
    Bed bed = aBed().withBedType(bedType).withCapacity(capacity).build();
    Bed otherBed = aBed().withNullAttributes().withBedType(bedType).withCapacity(capacity).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentAttribute_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    int capacity = 100;
    int otherCapacity = 200;
    Bed bed = aBed().withBedType(bedType).withCapacity(capacity).build();
    Bed otherBed =
        aBed().withNullAttributes().withBedType(bedType).withCapacity(otherCapacity).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllDifferentAttributes_shouldReturnFalse() {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedtype = BedTypes.MEMORY_FOAM;
    int capacity = 100;
    int otherCapacity = 200;
    Bed bed = aBed().withBedType(bedType).withCapacity(capacity).build();
    Bed otherBed =
        aBed().withNullAttributes().withBedType(otherBedtype).withCapacity(otherCapacity).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }
}
