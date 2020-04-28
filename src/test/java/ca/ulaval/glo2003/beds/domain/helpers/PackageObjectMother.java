package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.transactions.domain.Price;

public class PackageObjectMother {

  private PackageObjectMother() {}

  public static Packages createPackageName() {
    return randomEnum(Packages.class);
  }

  public static Price createPricePerNight() {
    return createPrice();
  }
}
