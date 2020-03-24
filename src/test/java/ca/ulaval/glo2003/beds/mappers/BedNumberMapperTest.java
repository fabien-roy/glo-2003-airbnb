package ca.ulaval.glo2003.beds.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedNumberMapperTest {

  private BedNumberMapper bedNumberMapper;

  @BeforeEach
  public void setUpMapper() {
    bedNumberMapper = new BedNumberMapper();
  }

  @Test
  public void fromString_withValidNumber_shouldReturnValidUUID() {
    UUID expectedUUID = UUID.randomUUID();
    String number = expectedUUID.toString();

    UUID uuid = bedNumberMapper.fromString(number);

    assertEquals(expectedUUID, uuid);
  }

  @Test
  public void fromString_withInvalidNumber_shouldThrowBedNotFoundException() {
    String invalidNumber = "invalidNumber";

    assertThrows(BedNotFoundException.class, () -> bedNumberMapper.fromString(invalidNumber));
  }
}
