package ca.ulaval.glo2003.locations.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ZipCodeTest {

  private static final String value = "12345";
  private static final ZipCode zipCode = new ZipCode(value);

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotZipCode() {
    Object other = new Object();

    boolean result = zipCode.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenZipCodesAreNotEqual() {
    ZipCode other = new ZipCode("23456");

    boolean result = zipCode.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenZipCodesAreEqual() {
    ZipCode other = new ZipCode(value);

    boolean result = zipCode.equals(other);

    assertTrue(result);
  }
}
