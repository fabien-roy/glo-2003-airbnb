package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother;
import ca.ulaval.glo2003.beds.rest.exceptions.ExceedingAccommodationCapacityException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class BedFactoryTest {

  BedFactory bedFactory;

  @BeforeEach
  public void setUpFactory() {
    bedFactory = new BedFactory();
  }

  @Test
  public void create_shouldSetBedNumber() {
    Bed bed = aBed().build();

    bed = bedFactory.create(bed);

    assertNotNull(bed.getNumber());
  }

  @Test
  public void create_shouldSetDifferentBedNumbers() {
    Bed bed = aBed().build();
    Bed otherBed = aBed().build();

    bed = bedFactory.create(bed);
    otherBed = bedFactory.create(otherBed);

    assertNotEquals(bed.getNumber(), otherBed.getNumber());
  }

  @ParameterizedTest
  @EnumSource(BedTypes.class)
  public void create_withExceedingCapacity_shouldThrowExceedingAccommodationCapacityException(
      BedTypes bedType) {
    int maxCapacity = BedTypesCapacities.get(bedType);
    Bed bed = aBed().withBedType(bedType).withCapacity(maxCapacity + 1).build();

    assertThrows(ExceedingAccommodationCapacityException.class, () -> bedFactory.create(bed));
  }

  @Test
  public void create_withNoDependencies_shouldNoThrow() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    List<Package> testPackage =
        Collections.singletonList(
            new Package(packageName, PackageObjectMother.createPricePerNight()));
    Bed bed = aBed().withPackages(testPackage).build();

    assertDoesNotThrow(() -> bedFactory.create(bed));
  }

  @Test
  public void create_withoutAllYouCanDrinkDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    List<Package> testPackage =
        Collections.singletonList(
            new Package(packageName, PackageObjectMother.createPricePerNight()));
    Bed bed = aBed().withPackages(testPackage).build();

    assertThrows(AllYouCanDrinkDependencyException.class, () -> bedFactory.create(bed));
  }

  @Test
  public void create_withoutAllYouCanDrinkDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    List<Package> testPackage =
        Collections.singletonList(
            new Package(packageName, PackageObjectMother.createPricePerNight()));
    Bed bed = aBed().withPackages(testPackage).build();

    assertThrows(SweetToothDependencyException.class, () -> bedFactory.create(bed));
  }
}
