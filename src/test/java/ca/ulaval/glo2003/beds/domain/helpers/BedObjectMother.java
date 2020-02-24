package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import com.github.javafaker.Faker;
import java.util.*;

public class BedObjectMother {

  private BedObjectMother() {}

  public static UUID createBedNumber() {
    return UUID.randomUUID();
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
    return Collections.singletonList(aPackage().build());
  }
}
