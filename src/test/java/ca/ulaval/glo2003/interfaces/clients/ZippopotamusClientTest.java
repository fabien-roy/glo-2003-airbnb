package ca.ulaval.glo2003.interfaces.clients;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
import org.junit.jupiter.api.Test;

class ZippopotamusClientTest {

  @Test
  void validateZipCode_withValidZipCode_shouldNotThrow() {
    String zipCode = "12345";
    ZippopotamusClient zippopotamusClient = new ZippopotamusClient(zipCode);

    assertDoesNotThrow(zippopotamusClient::validateZipCode);
  }

  @Test
  void validateZipCode_withNonExistingZipCode_shouldThrowNonExistingZipCodeException() {
    String zipCode = "00000";
    ZippopotamusClient zippopotamusClient = new ZippopotamusClient(zipCode);

    assertThrows(NonExistingZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  @Test
  void validateZipCode_withInvalidZipCodeLength_shouldThrowNonExistingZipCodeException() {
    String zipCode = "0000";
    ZippopotamusClient zippopotamusClient = new ZippopotamusClient(zipCode);

    assertThrows(InvalidZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  @Test
  void validateZipCode_withNotAllNumericZipCode_shouldThrowNonExistingZipCodeException() {
    String zipCode = "0R00A";
    ZippopotamusClient zippopotamusClient = new ZippopotamusClient(zipCode);

    assertThrows(InvalidZipCodeException.class, zippopotamusClient::validateZipCode);
  }

  @Test
  void
      validateZipCode_withoutInternetConnection_shouldThrowUnreachableZippopotamusServerException() {
    // TODO Find a way to disconnect network
    String zipCode = "12345";

    assertThrows(
        UnreachableZippopotamusServerException.class, () -> new ZippopotamusClient(zipCode));
  }
}
