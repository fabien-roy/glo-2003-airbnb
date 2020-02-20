package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PackageTest {

  @Test
  public void equals_withSameNameAndPrice_shouldReturnTrue() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    BigDecimal price = BigDecimal.valueOf(100);
    Package aPackage = new Package(packageName, price);
    Package otherPackage = new Package(packageName, price);

    boolean equals = aPackage.equals(otherPackage);

    assertTrue(equals);
  }

  @Test
  public void equals_withDifferentName_shouldReturnFalse() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    PackageNames otherPackageName = PackageNames.ALL_YOU_CAN_DRINK;
    BigDecimal price = BigDecimal.valueOf(100);
    Package aPackage = new Package(packageName, price);
    Package otherPackage = new Package(otherPackageName, price);

    boolean equals = aPackage.equals(otherPackage);

    assertFalse(equals);
  }

  @Test
  public void equals_withDifferentPrice_shouldReturnFalse() {
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    BigDecimal price = BigDecimal.valueOf(100);
    BigDecimal otherPrice = BigDecimal.valueOf(200);
    Package aPackage = new Package(packageName, price);
    Package otherPackage = new Package(packageName, otherPrice);

    boolean equals = aPackage.equals(otherPackage);

    assertFalse(equals);
  }
}
