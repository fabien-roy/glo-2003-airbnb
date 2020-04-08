package ca.ulaval.glo2003.locations.converters;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ZipCodeConverterTest {

  private static ZipCodeConverter zipCodeConverter;

  private static final ZipCode zipCode = createZipCode();

  @BeforeAll
  public static void setUpmMapper() {
    zipCodeConverter = new ZipCodeConverter();
  }

  @Test
  void fromString_shouldConvertZipCode() {
    ZipCode actualZipCode = zipCodeConverter.fromString(zipCode.toString());

    assertEquals(zipCode, actualZipCode);
  }

  @Test
  void fromString_withNullZipCode_shouldThrowNonExistingZipCodeException() {
    String nullZipCode = null;

    assertThrows(InvalidZipCodeException.class, () -> zipCodeConverter.fromString(nullZipCode));
  }

  @Test
  void fromString_withInvalidZipCodeLength_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "0000";

    assertThrows(InvalidZipCodeException.class, () -> zipCodeConverter.fromString(invalidZipCode));
  }

  @Test
  void fromString_withNotAllNumericZipCode_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "00A2E";

    assertThrows(InvalidZipCodeException.class, () -> zipCodeConverter.fromString(invalidZipCode));
  }
}
