package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.List;

public class BedRequestObjectMother {

  private BedRequestObjectMother() {}

  public static String createOwnerPublicKey() {
    return createPublicKey().getValue();
  }

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
  }

  public static String createBedType() {
    return randomEnum(BedTypes.class).toString();
  }

  public static String createCleaningFrequency() {
    return randomEnum(CleaningFrequencies.class).toString();
  }

  public static List<String> createBloodTypes() {
    return Collections.singletonList(randomEnum(BloodTypes.class).toString());
  }

  public static int createCapacity() {
    return Faker.instance().number().numberBetween(1, 1000);
  }

  public static List<PackageRequest> createPackages() {
    return Collections.singletonList(
        new PackageRequest(createPackageName(), createPackagePricePerNight()));
  }

  private static String createPackageName() {
    return randomEnum(Packages.class).toString();
  }

  private static double createPackagePricePerNight() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }

  public static String createLodgingMode() {
    return randomEnum(LodgingModes.class).toString();
  }
}
