package ca.ulaval.glo2003.beds.converters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.domain.BedNumber;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BedNumberConverterTest {

  private static BedNumberConverter bedNumberConverter;

  private BedNumber bedNumber = createBedNumber();

  @BeforeAll
  public static void setUpConverter() {
    bedNumberConverter = new BedNumberConverter();
  }

  @Test
  public void fromString_shouldConvertBedNumber() {
    BedNumber actualBedNumber = bedNumberConverter.fromString(bedNumber.toString());

    assertEquals(bedNumber, actualBedNumber);
  }

  @Test
  public void fromString_withInvalidNumber_shouldThrowBedNotFoundException() {
    String invalidNumber = "invalidNumber";

    assertThrows(BedNotFoundException.class, () -> bedNumberConverter.fromString(invalidNumber));
  }
}
