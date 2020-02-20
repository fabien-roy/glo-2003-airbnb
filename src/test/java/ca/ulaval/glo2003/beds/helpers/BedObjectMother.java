package ca.ulaval.glo2003.beds.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.*;

public class BedObjectMother {

  // TODO : This was built during testing for Bed.matches(...)
  // TODO : Might turn out irrelevant

  private BedObjectMother() {}

  public static UUID createBedNumber() {
    return UUID.fromString(Faker.instance().internet().uuid());
  }

  public static String createOwnerPublicKey() {
    return Faker.instance().chuckNorris().fact();
  }

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
  }

  public static BedTypes createBedType() {
    return randomEnum(BedTypes.class);
  }

  public static CleaningFrequencies createCleaningFrequency() {
    return randomEnum(CleaningFrequencies.class);
  }

  public static List<BloodTypes> createBloodTypes() {
    return Collections.singletonList(randomEnum(BloodTypes.class));
  }

  public static int createCapacity() {
    return Faker.instance().number().numberBetween(1, 1000);
  }

  public static List<Package> createPackages() {
    return Collections.singletonList(new Package(createPackageName(), createPackagePrice()));
  }

  private static PackageNames createPackageName() {
    return randomEnum(PackageNames.class);
  }

  private static BigDecimal createPackagePrice() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return BigDecimal.valueOf(randomDouble);
  }
}
