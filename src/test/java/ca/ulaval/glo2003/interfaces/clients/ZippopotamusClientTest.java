package ca.ulaval.glo2003.interfaces.clients;

import static ca.ulaval.glo2003.interfaces.clients.ZippopotamusClient.validateZipCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZippopotamusClientTest {

  @Test
  void validateZipCode_withValidZipCode_shouldNotThrow() {
    String zipCode = "12345";
    assertDoesNotThrow(() -> validateZipCode(zipCode));
  }
}
