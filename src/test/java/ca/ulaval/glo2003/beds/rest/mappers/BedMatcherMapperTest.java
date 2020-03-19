package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import java.util.*;
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
    Map<String, String[]> params = new HashMap<>();

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertNull(bedMatcher.getBedType());
    assertNull(bedMatcher.getCleaningFrequency());
    assertNull(bedMatcher.getBloodTypes());
    assertEquals(0, bedMatcher.getMinCapacity());
    assertNull(bedMatcher.getPackageName());
  }

  @Test
  public void fromRequestParams_withBedType_shouldReturnBedMatcherWithBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    Map<String, String[]> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, new String[] {expectedBedType.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedBedType, bedMatcher.getBedType());
  }

  @Test
  public void fromRequestParams_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, new String[] {"invalidBedType"});

    assertThrows(InvalidBedTypeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withCleaningFrequency_shouldReturnBedMatcherWithCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    Map<String, String[]> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {expectedCleaningFrequency.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCleaningFrequency, bedMatcher.getCleaningFrequency());
  }

  @Test
  public void
      fromRequestParams_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {"invalidCleaningFrequency"});

    assertThrows(
        InvalidCleaningFrequencyException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withSingleBloodType_shouldReturnBedMatcherWithBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    Map<String, String[]> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, new String[] {expectedBloodType.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(1, bedMatcher.getBloodTypes().size());
    assertEquals(expectedBloodType, bedMatcher.getBloodTypes().get(0));
  }

  @Test
  public void fromRequestParams_withMultipleBloodTypes_shouldReturnBedMatcherWithBloodTypes() {
    List<BloodTypes> expectedBloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    String[] expectedBloodTypeStrings =
        new String[] {expectedBloodTypes.get(0).toString(), expectedBloodTypes.get(1).toString()};
    Map<String, String[]> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, expectedBloodTypeStrings);

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(2, bedMatcher.getBloodTypes().size());
    assertEquals(expectedBloodTypes, bedMatcher.getBloodTypes());
  }

  @Test
  public void fromRequestParams_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, new String[] {"invalidBloodTypes"});

    assertThrows(
        InvalidBloodTypesException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withCapacity_shouldReturnBedMatcherWithCapacity() {
    int expectedCapacity = 600;
    Map<String, String[]> params = new HashMap<>();
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(expectedCapacity)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCapacity, bedMatcher.getMinCapacity());
  }

  @Test
  public void fromRequestParams_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(MIN_CAPACITY_PARAM, new String[] {"invalidCapacity"});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeCapacity_shouldThrowMinimalCapacityException() {
    int invalidCapacity = -1;
    Map<String, String[]> params = new HashMap<>();
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(invalidCapacity)});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withPackageName_shouldReturnBedMatcherWithPackage() {
    Packages expectedPackageName = Packages.BLOODTHIRSTY;
    Map<String, String[]> params = new HashMap<>();
    params.put(PACKAGE_NAME_PARAM, new String[] {expectedPackageName.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedPackageName, bedMatcher.getPackageName());
  }

  @Test
  public void fromRequestParams_withInvalidPackageName_shouldThrowInvalidPackageException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(PACKAGE_NAME_PARAM, new String[] {"invalidPackageName"});

    assertThrows(InvalidPackageException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withInvalidMaxDistance_shouldThrowInvalidDistanceException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(MAX_DISTANCE_PARAM, new String[] {"invalidDistance"});
    params.put(ORIGIN_PARAM, new String[] {"12345"});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeMaxDistance_shouldThrowInvalidDistanceException() {
    int invalidMaxDistance = -1;
    Map<String, String[]> params = new HashMap<>();
    params.put(MAX_DISTANCE_PARAM, new String[] {Integer.toString(invalidMaxDistance)});
    params.put(ORIGIN_PARAM, new String[] {"12345"});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNoMaxDistance_shouldBeSetToDefaultMaxDistance() {
    Map<String, String[]> params = new HashMap<>();
    params.put(ORIGIN_PARAM, new String[] {"12345"});
    int expectedMaxDistance = 10;
    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedMaxDistance, bedMatcher.getMaxDistance());
  }

  @Test
  public void
      fromRequestParams_withMaxDistanceAndWithNoOrigin_shouldThrowInvalidDistanceWithoutOriginException() {
    Map<String, String[]> params = new HashMap<>();
    String maxDistance = "10";
    params.put(MAX_DISTANCE_PARAM, new String[] {maxDistance});

    assertThrows(
        MaxDistanceWithoutOriginException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withLodgingMode_shouldReturnBedMatcherWithLodgingMode() {
    LodgingModes expectedLodgingMode = LodgingModes.COHABITATION;
    Map<String, String[]> params = new HashMap<>();
    params.put(LODGING_MODE_PARAM, new String[] {expectedLodgingMode.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedLodgingMode, bedMatcher.getLodgingMode());
  }

  @Test
  public void fromRequestParams_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    Map<String, String[]> params = new HashMap<>();
    params.put(LODGING_MODE_PARAM, new String[] {"invalidLodgingMode"});

    assertThrows(
        InvalidLodgingModeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }
}
