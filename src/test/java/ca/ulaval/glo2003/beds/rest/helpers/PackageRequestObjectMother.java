package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.PackageNames;
import com.github.javafaker.Faker;

public class PackageRequestObjectMother {

  private PackageRequestObjectMother() {}

  public static String createPackageName() {
    return randomEnum(PackageNames.class).toString();
  }

  public static double createPricePerNight() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }
}
