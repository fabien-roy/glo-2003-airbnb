package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.transactions.domain.Price;
import com.github.javafaker.Faker;

public class PackageObjectMother {

  private PackageObjectMother() {}

  public static Packages createPackageName() {
    return randomEnum(Packages.class);
  }

  public static Price createPricePerNight() {
    double randomDouble = Faker.instance().number().randomDouble(2, 100, 1000);
    return new Price(randomDouble);
  }
}
