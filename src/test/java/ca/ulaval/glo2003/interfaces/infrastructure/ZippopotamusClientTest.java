package ca.ulaval.glo2003.interfaces.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.NonExistingZipCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZippopotamusClientTest {

  private ZippopotamusClient zippopotamusClient;

  @BeforeEach
  public void setUpMapper() {
    zippopotamusClient = new ZippopotamusClient();
  }

  @Test
  void validateZipCode_withValidZipCode_shouldNotThrow() {
    String zipCode = "12345";
    ZipCode expectedZipCode = new ZipCode(zipCode);
    zippopotamusClient.initiate(zipCode);

    assertEquals(expectedZipCode, zippopotamusClient.getZipCode());
  }

  @Test
  void validateZipCode_withNonExistingZipCode_shouldThrowNonExistingZipCodeException() {
    String zipCode = "00000";
    zippopotamusClient.initiate(zipCode);

    assertThrows(NonExistingZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  @Test
  void validateZipCode_withInvalidZipCodeLength_shouldThrowNonExistingZipCodeException() {
    String zipCode = "0000";
    zippopotamusClient.initiate(zipCode);

    assertThrows(InvalidZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  @Test
  void validateZipCode_withNotAllNumericZipCode_shouldThrowNonExistingZipCodeException() {
    String zipCode = "0R00A";
    zippopotamusClient.initiate(zipCode);

    assertThrows(InvalidZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  // TODO : Repair this test
  /*
  @Test
  void
      validateZipCode_withoutInternetConnection_shouldThrowUnreachableZippopotamusServerException() {
    String zipCode = "12345";

    assertThrows(
        UnreachableZippopotamusServerException.class, () -> zippopotamusClient.initiate(zipCode));
  }
  */
}
