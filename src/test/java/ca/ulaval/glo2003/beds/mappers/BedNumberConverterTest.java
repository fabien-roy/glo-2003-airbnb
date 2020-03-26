package ca.ulaval.glo2003.beds.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BedNumberConverterTest {

  private static BedNumberConverter bedNumberConverter;

  private UUID bedNumber = createBedNumber();

  @BeforeAll
  public static void setUpMapper() {
    bedNumberConverter = new BedNumberConverter();
  }

  @Test
  public void fromString_shouldConvertBedNumber() {
    UUID actualBedNumber = bedNumberConverter.fromString(bedNumber.toString());

    assertEquals(bedNumber, actualBedNumber);
  }

  @Test
  public void fromString_withInvalidNumber_shouldThrowBedNotFoundException() {
    String invalidNumber = "invalidNumber";

    assertThrows(BedNotFoundException.class, () -> bedNumberConverter.fromString(invalidNumber));
  }

  @Test
  public void toString_shouldConvertBedNumber() {
    String actualString = bedNumberConverter.toString(bedNumber);

    assertEquals(bedNumber.toString(), actualString);
  }
}
