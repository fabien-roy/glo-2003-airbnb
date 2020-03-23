package ca.ulaval.glo2003.locations.domain;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LocationTest {

  private static Location location;
  private static String zipCode = createZipCode();

  @BeforeAll
  public static void setUpLocation() {
    location = aLocation().withZipCode(zipCode).build();
  }

  // TODO : Test isWithinRadius

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotZipCode() {
    String other = "12345";

    boolean result = location.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenZipCodesAreNotEqual() {
    Location other = aLocation().withZipCode("other").build();

    boolean result = location.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenZipCodesAreEqual() {
    Location other = aLocation().withZipCode(zipCode).build();

    boolean result = location.equals(other);

    assertTrue(result);
  }
}
