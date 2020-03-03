package ca.ulaval.glo2003.interfaces.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MapQuestClientTest {

  @Test
  void validation_withValidZipCode_shouldReturnTrue() {
    String validZipCode = "10001";

    assertTrue(MapQuestClient.validateZipCode(validZipCode));
  }
}
