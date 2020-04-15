package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedNumberTest {

  private static BedNumber bedNumber;
  private static BedNumber otherBedNumber;

  @BeforeEach
  public void resetBedNumbers() {
    bedNumber = createBedNumber();
    otherBedNumber = createBedNumber();
  }

  @Test
  public void toUUID_shouldReturnValueAsUUID() {
    UUID value = UUID.randomUUID();
    bedNumber = new BedNumber(value);

    UUID actualValue = bedNumber.toUUID();

    assertEquals(value, actualValue);
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBedNumber() {
    Object object = new Object();

    boolean result = bedNumber.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    boolean result = bedNumber.equals(otherBedNumber);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    otherBedNumber = new BedNumber(bedNumber.toUUID());

    boolean result = bedNumber.equals(otherBedNumber);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldReturnValueHashCode() {
    int hashCode = bedNumber.hashCode();

    assertEquals(bedNumber.toUUID().hashCode(), hashCode);
  }
}
