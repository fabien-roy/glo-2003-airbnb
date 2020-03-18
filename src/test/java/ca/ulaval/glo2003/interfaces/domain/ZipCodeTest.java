package ca.ulaval.glo2003.interfaces.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZipCodeTest {

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotZipCode() {
    String zipValue = "12345";
    ZipCode zipCode = new ZipCode(zipValue);

    boolean result = zipCode.equals(zipValue);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    String value = "000000";
    String otherValue = "12345";
    ZipCode zipCode = new ZipCode(value);
    ZipCode otherZipCode = new ZipCode(otherValue);

    boolean result = zipCode.equals(otherValue);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    String value = "12345";
    ZipCode zipCode = new ZipCode(value);
    ZipCode otherZipCode = new ZipCode(value);

    boolean result = zipCode.equals(otherZipCode);

    assertTrue(result);
  }
}
