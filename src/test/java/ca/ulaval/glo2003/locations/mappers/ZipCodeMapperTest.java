package ca.ulaval.glo2003.locations.mappers;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ZipCodeMapperTest {

  private static ZipCodeMapper zipCodeMapper;

  private static final ZipCode zipCode = createZipCode();

  @BeforeAll
  public static void setUpmMapper() {
    zipCodeMapper = new ZipCodeMapper();
  }

  @Test
  void fromString_shouldMapZipCode() {
    ZipCode actualZipCode = zipCodeMapper.fromString(zipCode.getValue());

    assertEquals(actualZipCode, zipCode);
  }

  @Test
  void fromString_withInvalidZipCodeLength_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "0000";

    assertThrows(InvalidZipCodeException.class, () -> zipCodeMapper.fromString(invalidZipCode));
  }

  @Test
  void fromString_withNotAllNumericZipCode_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "00A2E";

    assertThrows(InvalidZipCodeException.class, () -> zipCodeMapper.fromString(invalidZipCode));
  }
}
