package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMapper.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.exceptions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMatcherMapperTest {

  private BedMatcherMapper bedMatcherMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMatcherMapper = new BedMatcherMapper();
  }

  @Test
  public void fromRequestParams_withNoParams_shouldReturnBedMatcherWithNullAttributes() {
    Map<String, String> params = new HashMap<>();

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertNull(bedMatcher.getBedType());
    assertNull(bedMatcher.getCleaningFrequency());
    assertNull(bedMatcher.getBloodTypes());
    assertEquals(0, bedMatcher.getCapacity());
    assertNull(bedMatcher.getPackages());
  }

  @Test
  public void fromRequestParams_withBedType_shouldReturnBedMatcherWithBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    Map<String, String> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, expectedBedType.toString());

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedBedType, bedMatcher.getBedType());
  }

  @Test
  public void fromRequestParams_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    Map<String, String> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, "invalidBedType");

    assertThrows(InvalidBedTypeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withCleaningFrequency_shouldReturnBedMatcherWithCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    Map<String, String> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, expectedCleaningFrequency.toString());

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCleaningFrequency, bedMatcher.getCleaningFrequency());
  }

  @Test
  public void
      fromRequestParams_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    Map<String, String> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, "invalidCleaningFrequency");

    assertThrows(
        InvalidCleaningFrequencyException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withBloodType_shouldReturnBedMatcherWithBloodType() {

    BloodTypes expectedBloodTypes = BloodTypes.O_MINUS;
    Map<String, String> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, expectedBloodTypes.toString());

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(1, bedMatcher.getBloodTypes().size());
    assertEquals(expectedBloodTypes, bedMatcher.getBloodTypes().get(0));
  }

  @Test
  public void fromRequestParams_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    Map<String, String> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, "invalidBloodTypes");

    assertThrows(
        InvalidBloodTypesException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withCapacity_shouldReturnBedMatcherWithCapacity() {
    int expectedCapacity = 600;
    Map<String, String> params = new HashMap<>();
    params.put(CAPACITY_PARAM, Integer.toString(expectedCapacity));

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCapacity, bedMatcher.getCapacity());
  }

  @Test
  public void fromRequestParams_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    Map<String, String> params = new HashMap<>();
    params.put(CAPACITY_PARAM, "invalidCapacity");

    assertThrows(
        InvalidMinimalCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeCapacity_shouldThrowMinimalCapacityException() {
    int invalidCapacity = -1;
    Map<String, String> params = new HashMap<>();
    params.put(CAPACITY_PARAM, Integer.toString(invalidCapacity));

    assertThrows(
        InvalidMinimalCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withPackage_shouldReturnBedMatcherWithPackage() {
    PackageNames expectedPackageName = PackageNames.BLOODTHIRSTY;
    Map<String, String> params = new HashMap<>();
    params.put(PACKAGE_NAME_PARAM, expectedPackageName.toString());

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedPackageName, bedMatcher.getPackages().get(0).getName());
  }

  @Test
  public void fromRequestParams_withInvalidPackage_shouldThrowInvalidPackageException() {
    Map<String, String> params = new HashMap<>();
    params.put(PACKAGE_NAME_PARAM, "invalidPackageName");

    assertThrows(InvalidPackageException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }
}
