package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingDateMapper;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMatcherMapperTest {

  private static BedMatcherMapper bedMatcherMapper;
  private static BookingDateMapper bookingDateMapper;

  private Map<String, String[]> params = new HashMap<>();
  private BookingDate arrivalDate = new BookingDate(LocalDate.now());

  @BeforeAll
  public static void setUpMapper() {
    bookingDateMapper = mock(BookingDateMapper.class);
    bedMatcherMapper = new BedMatcherMapper(bookingDateMapper);
  }

  @BeforeEach
  public void setUpMocks() {
    when(bookingDateMapper.fromString(arrivalDate.getValue().toString())).thenReturn(arrivalDate);
  }

  @Test
  public void fromRequestParams_withNoParams_shouldReturnBedMatcherWithNullAttributes() {
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
    params.put(BED_TYPE_PARAM, new String[] {expectedBedType.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedBedType, bedMatcher.getBedType());
  }

  @Test
  public void fromRequestParams_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    params.put(BED_TYPE_PARAM, new String[] {"invalidBedType"});

    assertThrows(InvalidBedTypeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withCleaningFrequency_shouldReturnBedMatcherWithCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {expectedCleaningFrequency.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCleaningFrequency, bedMatcher.getCleaningFrequency());
  }

  @Test
  public void
      fromRequestParams_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {"invalidCleaningFrequency"});

    assertThrows(
        InvalidCleaningFrequencyException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withSingleBloodType_shouldReturnBedMatcherWithBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
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
    params.put(BLOOD_TYPES_PARAM, expectedBloodTypeStrings);

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(2, bedMatcher.getBloodTypes().size());
    assertEquals(expectedBloodTypes, bedMatcher.getBloodTypes());
  }

  @Test
  public void fromRequestParams_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    params.put(BLOOD_TYPES_PARAM, new String[] {"invalidBloodTypes"});

    assertThrows(
        InvalidBloodTypesException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withCapacity_shouldReturnBedMatcherWithCapacity() {
    int expectedCapacity = 600;
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(expectedCapacity)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedCapacity, bedMatcher.getMinCapacity());
  }

  @Test
  public void fromRequestParams_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    params.put(MIN_CAPACITY_PARAM, new String[] {"invalidCapacity"});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeCapacity_shouldThrowMinimalCapacityException() {
    int invalidCapacity = -1;
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(invalidCapacity)});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withPackageName_shouldReturnBedMatcherWithPackage() {
    Packages expectedPackageName = Packages.BLOODTHIRSTY;
    params.put(PACKAGE_NAME_PARAM, new String[] {expectedPackageName.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedPackageName, bedMatcher.getPackageName());
  }

  @Test
  public void fromRequestParams_withInvalidPackageName_shouldThrowInvalidPackageException() {
    params.put(PACKAGE_NAME_PARAM, new String[] {"invalidPackageName"});

    assertThrows(InvalidPackageException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withInvalidMaxDistance_shouldThrowInvalidDistanceException() {
    params.put(MAX_DISTANCE_PARAM, new String[] {"invalidDistance"});
    params.put(ORIGIN_PARAM, new String[] {"12345"});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeMaxDistance_shouldThrowInvalidDistanceException() {
    int invalidMaxDistance = -1;
    params.put(MAX_DISTANCE_PARAM, new String[] {Integer.toString(invalidMaxDistance)});
    params.put(ORIGIN_PARAM, new String[] {"12345"});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNoMaxDistance_shouldBeSetToDefaultMaxDistance() {
    params.put(ORIGIN_PARAM, new String[] {"12345"});
    int expectedMaxDistance = 10;
    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedMaxDistance, bedMatcher.getMaxDistance());
  }

  @Test
  public void
      fromRequestParams_withMaxDistanceAndWithNoOrigin_shouldThrowInvalidDistanceWithoutOriginException() {
    String maxDistance = "10";
    params.put(MAX_DISTANCE_PARAM, new String[] {maxDistance});

    assertThrows(
        MaxDistanceWithoutOriginException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withLodgingMode_shouldReturnBedMatcherWithLodgingMode() {
    LodgingModes expectedLodgingMode = LodgingModes.COHABITATION;
    params.put(LODGING_MODE_PARAM, new String[] {expectedLodgingMode.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(expectedLodgingMode, bedMatcher.getLodgingMode());
  }

  @Test
  public void fromRequestParams_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    params.put(LODGING_MODE_PARAM, new String[] {"invalidLodgingMode"});

    assertThrows(
        InvalidLodgingModeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withArrivalDate_shouldReturnBedMatcherWithArrivalDate() {
    params.put(ARRIVAL_DATE_PARAM, new String[] {arrivalDate.getValue().toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(arrivalDate, bedMatcher.getArrivalDate());
  }
}
