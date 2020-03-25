package ca.ulaval.glo2003.beds.mappers;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BloodTypesMapperTest {

  private static BloodTypesMapper bloodTypesMapper;

  private static BloodTypes bloodType;
  private static BloodTypes otherBloodType;
  private static List<BloodTypes> bloodTypes;

  private static List<String> bloodTypeStrings;

  @BeforeAll
  public static void setUpMapper() {
    bloodTypesMapper = new BloodTypesMapper();
  }

  @BeforeEach
  public void setUpMocks() {
    bloodType = BloodTypes.O_MINUS;
    otherBloodType = BloodTypes.O_MINUS;
    bloodTypes = Arrays.asList(bloodType, otherBloodType);
    bloodTypeStrings = Arrays.asList(bloodType.toString(), otherBloodType.toString());
  }

  @Test
  public void fromStrings_withSingleBloodType_shouldMapSingleBloodType() {
    bloodTypeStrings = bloodTypeStrings.subList(0, 1);

    bloodTypes = bloodTypesMapper.fromStrings(bloodTypeStrings);

    assertEquals(1, bloodTypes.size());
    assertEquals(bloodType, bloodTypes.get(0));
  }

  @Test
  public void fromStrings_withMultipleBloodTypes_shouldMapMultipleBloodTypes() {
    bloodTypes = bloodTypesMapper.fromStrings(bloodTypeStrings);

    assertEquals(2, bloodTypes.size());
    assertTrue(bloodTypes.contains(bloodType));
    assertTrue(bloodTypes.contains(otherBloodType));
  }

  // TODO : withInvalidBloodTypes (String instead of array) (also test numerical)

  @Test
  public void fromStrings_withoutBloodTypes_shouldThrowInvalidBloodTypesException() {
    bloodTypeStrings = null;

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypesMapper.fromStrings(bloodTypeStrings));
  }

  @Test
  public void fromStrings_withEmptyBloodTypes_shouldThrowInvalidBloodTypesException() {
    bloodTypeStrings = Collections.emptyList();

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypesMapper.fromStrings(bloodTypeStrings));
  }

  @Test
  public void fromStrings_witNullBloodType_shouldThrowInvalidBloodTypeException() {
    bloodTypeStrings = Collections.singletonList(null);

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypesMapper.fromStrings(bloodTypeStrings));
  }

  @Test
  public void fromStrings_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    bloodTypeStrings = Collections.singletonList("invalidBloodType");

    assertThrows(
        InvalidBloodTypesException.class, () -> bloodTypesMapper.fromStrings(bloodTypeStrings));
  }

  @Test
  public void toStrings_withSingleBloodType_shouldMapBloodType() {
    bloodTypes = bloodTypes.subList(0, 1);

    bloodTypeStrings = bloodTypesMapper.toStrings(bloodTypes);

    assertEquals(1, bloodTypeStrings.size());
    assertEquals(bloodType.toString(), bloodTypeStrings.get(0));
  }

  @Test
  public void toStrings_withMultipleBloodTypes_shouldMapBloodTypes() {
    bloodTypeStrings = bloodTypesMapper.toStrings(bloodTypes);

    assertEquals(2, bloodTypeStrings.size());
    assertTrue(
        bloodTypeStrings.stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(bloodType.toString())));
    assertTrue(
        bloodTypeStrings.stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(otherBloodType.toString())));
  }
}
