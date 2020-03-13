package ca.ulaval.glo2003.interfaces.clients;

import static ca.ulaval.glo2003.interfaces.clients.ZippopotamusClient.validateZipCode;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import org.junit.jupiter.api.Test;

class ZippopotamusClientTest {

  @Test
  void validateZipCode_withValidZipCode_shouldNotThrow() {
    String zipCode = "12345";
    assertDoesNotThrow(() -> validateZipCode(zipCode));
  }

  @Test
  void validateZipCode_withNonExistingZipCode_shouldThrowNonExistingZipCodeException() {
    String zipCode = "00000";
    assertThrows(NonExistingZipCodeException.class, () -> validateZipCode(zipCode));
  }
}
