package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import java.math.BigDecimal;

public class PackageBuilder {

  private PackageBuilder() {}

  private PackageNames DEFAULT_NAME = createPackageName();
  private PackageNames name = DEFAULT_NAME;

  private BigDecimal DEFAULT_PRICE_PER_NIGHT = createPricePerNight();
  private BigDecimal pricePerNight = DEFAULT_PRICE_PER_NIGHT;

  public static PackageBuilder aPackage() {
    return new PackageBuilder();
  }

  public PackageBuilder withName(PackageNames name) {
    this.name = name;
    return this;
  }

  public PackageBuilder withPricePerNight(BigDecimal pricePerNight) {
    this.pricePerNight = pricePerNight;
    return this;
  }

  public Package build() {
    return new Package(name, pricePerNight);
  }
}
