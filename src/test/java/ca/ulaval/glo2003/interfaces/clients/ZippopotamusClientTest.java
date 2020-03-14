package ca.ulaval.glo2003.interfaces.clients;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
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
    zippopotamusClient.initiate(zipCode);

    assertDoesNotThrow(zippopotamusClient::validateZipCode);
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

  @Test
  void
      validateZipCode_withoutInternetConnection_shouldThrowUnreachableZippopotamusServerException() {
    // TODO Find a way to disconnect network
    String zipCode = "12345";

    assertThrows(
        UnreachableZippopotamusServerException.class, () -> zippopotamusClient.initiate(zipCode));
  }
}
