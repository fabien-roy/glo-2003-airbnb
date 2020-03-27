package ca.ulaval.glo2003.beds.converters;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BloodTypeConverterTest {

  private static BloodTypeConverter bloodTypeConverter;

  private static BloodTypes bloodType;
  private static BloodTypes otherBloodType;
  private static List<BloodTypes> bloodTypes;

  private static List<String> bloodTypeStrings;

  @BeforeAll
  public static void setUpConverter() {
    bloodTypeConverter = new BloodTypeConverter();
  }

  @BeforeEach
  public void setUpMocks() {
    bloodType = BloodTypes.O_MINUS;
    otherBloodType = BloodTypes.O_MINUS;
    bloodTypes = Arrays.asList(bloodType, otherBloodType);
    bloodTypeStrings = Arrays.asList(bloodType.toString(), otherBloodType.toString());
  }

  @Test
  public void fromStrings_withSingleBloodType_shouldConvertSingleBloodType() {
    bloodTypeStrings = bloodTypeStrings.subList(0, 1);

    bloodTypes = bloodTypeConverter.fromStrings(bloodTypeStrings);

    assertEquals(1, bloodTypes.size());
    assertEquals(bloodType, bloodTypes.get(0));
  }

  @Test
  public void fromStrings_withMultipleBloodTypes_shouldConvertMultipleBloodTypes() {
    bloodTypes = bloodTypeConverter.fromStrings(bloodTypeStrings);

    assertEquals(2, bloodTypes.size());
    assertTrue(bloodTypes.contains(bloodType));
    assertTrue(bloodTypes.contains(otherBloodType));
  }

  @Test
  public void fromStrings_withoutBloodType_shouldThrowInvalidBloodTypesException() {
    bloodTypeStrings = Collections.emptyList();

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypeConverter.fromStrings(bloodTypeStrings));
  }

  @Test
  public void fromStrings_witNullBloodType_shouldThrowInvalidBloodTypeException() {
    bloodTypeStrings = Collections.singletonList(null);

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypeConverter.fromStrings(bloodTypeStrings));
  }

  @Test
  public void fromStrings_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    bloodTypeStrings = Collections.singletonList("invalidBloodType");

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypeConverter.fromStrings(bloodTypeStrings));
  }

  @Test
  public void toStrings_withSingleBloodType_shouldConvertBloodType() {
    bloodTypes = bloodTypes.subList(0, 1);

    bloodTypeStrings = bloodTypeConverter.toStrings(bloodTypes);

    assertEquals(1, bloodTypeStrings.size());
    assertEquals(bloodType.toString(), bloodTypeStrings.get(0));
  }

  @Test
  public void toStrings_withMultipleBloodTypes_shouldConvertBloodTypes() {
    bloodTypeStrings = bloodTypeConverter.toStrings(bloodTypes);

    assertEquals(2, bloodTypeStrings.size());
    assertTrue(
        bloodTypeStrings.stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(bloodType.toString())));
    assertTrue(
        bloodTypeStrings.stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(otherBloodType.toString())));
  }
}
