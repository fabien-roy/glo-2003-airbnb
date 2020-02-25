package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;

public class PackageBuilder {

  private PackageBuilder() {}

  private PackageNames DEFAULT_NAME = createPackageName();
  private PackageNames name = DEFAULT_NAME;

  private Price DEFAULT_PRICE_PER_NIGHT = createPricePerNight();
  private Price pricePerNight = DEFAULT_PRICE_PER_NIGHT;

  public static PackageBuilder aPackage() {
    return new PackageBuilder();
  }

  public PackageBuilder withName(PackageNames name) {
    this.name = name;
    return this;
  }

  public PackageBuilder withPricePerNight(Price pricePerNight) {
    this.pricePerNight = pricePerNight;
    return this;
  }

  public Package build() {
    return new Package(name, pricePerNight);
  }
}
