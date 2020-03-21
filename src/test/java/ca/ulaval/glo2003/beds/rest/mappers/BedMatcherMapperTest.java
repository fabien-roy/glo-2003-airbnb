package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.rest.BedQueryMapBuilder.*;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.mappers.BookingDateMapper;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMatcherMapperTest {

  private static BedMatcherMapper bedMatcherMapper;
  private static BookingDateMapper bookingDateMapper;

  private Map<String, String[]> params = new HashMap<>();
  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private int minCapacity = 100;
  private Packages packageName = createPackageName();
  private BookingDate arrivalDate = new BookingDate(LocalDate.now());
  private int numberOfNights = 1;
  private LodgingModes lodgingMode = createLodgingMode();
  private ZipCode origin = createZipCode();
  private int maxDistance = 10;

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
    assertEquals(BedMatcher.UNSET_INT, bedMatcher.getMinCapacity());
    assertNull(bedMatcher.getPackage());
    assertNull(bedMatcher.getArrivalDate());
    assertEquals(BedMatcher.UNSET_INT, bedMatcher.getNumberOfNights());
    assertNull(bedMatcher.getLodgingMode());
    assertNull(bedMatcher.getOrigin());
    assertEquals(BedMatcher.UNSET_INT, bedMatcher.getMaxDistance());
  }

  @Test
  public void fromRequestParams_withBedType_shouldMapBedType() {
    params.put(BED_TYPE_PARAM, new String[] {bedType.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(bedType, bedMatcher.getBedType());
  }

  @Test
  public void fromRequestParams_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    params.put(BED_TYPE_PARAM, new String[] {"invalidBedType"});

    assertThrows(InvalidBedTypeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withCleaningFrequency_shouldMapCleaningFrequency() {
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {cleaningFrequency.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(cleaningFrequency, bedMatcher.getCleaningFrequency());
  }

  @Test
  public void
      fromRequestParams_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {"invalidCleaningFrequency"});

    assertThrows(
        InvalidCleaningFrequencyException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withSingleBloodType_shouldMapBloodType() {
    BloodTypes bloodType = BloodTypes.O_MINUS;
    params.put(BLOOD_TYPES_PARAM, new String[] {bloodType.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(1, bedMatcher.getBloodTypes().size());
    assertEquals(bloodType, bedMatcher.getBloodTypes().get(0));
  }

  @Test
  public void fromRequestParams_withMultipleBloodTypes_shouldMapBloodTypes() {
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    String[] bloodTypeStrings =
        new String[] {bloodTypes.get(0).toString(), bloodTypes.get(1).toString()};
    params.put(BLOOD_TYPES_PARAM, bloodTypeStrings);

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(2, bedMatcher.getBloodTypes().size());
    assertEquals(bloodTypes, bedMatcher.getBloodTypes());
  }

  @Test
  public void fromRequestParams_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    params.put(BLOOD_TYPES_PARAM, new String[] {"invalidBloodType"});

    assertThrows(
        InvalidBloodTypesException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withCapacity_shouldMapMinCapacity() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(minCapacity)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(minCapacity, bedMatcher.getMinCapacity());
  }

  @Test
  public void fromRequestParams_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    params.put(MIN_CAPACITY_PARAM, new String[] {"invalidCapacity"});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeCapacity_shouldThrowMinimalCapacityException() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(-1)});

    assertThrows(InvalidCapacityException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withPackageName_shouldMapPackage() {
    params.put(PACKAGE_NAME_PARAM, new String[] {packageName.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(packageName, bedMatcher.getPackage());
  }

  @Test
  public void fromRequestParams_withInvalidPackageName_shouldThrowInvalidPackageException() {
    params.put(PACKAGE_NAME_PARAM, new String[] {"invalidPackageName"});

    assertThrows(InvalidPackageException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withMinCapacityAndArrivalDate_shouldMapArrivalDate() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(minCapacity)});
    params.put(ARRIVAL_DATE_PARAM, new String[] {arrivalDate.getValue().toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(arrivalDate, bedMatcher.getArrivalDate());
  }

  @Test
  public void fromRequestParams_withoutArrivalDate_shouldMapDefaultArrivalDate() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(minCapacity)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(DEFAULT_ARRIVAL_DATE, bedMatcher.getArrivalDate());
  }

  @Test
  public void fromRequestParams_withMinCapacityAndNumberOfNights_shouldMapNumberOfNights() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(minCapacity)});
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(numberOfNights)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(numberOfNights, bedMatcher.getNumberOfNights());
  }

  @Test
  public void fromRequestParams_withoutNumberOfNights_shouldMapDefaultNumberOfNights() {
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(minCapacity)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(DEFAULT_NUMBER_OF_NIGHTS, bedMatcher.getNumberOfNights());
  }

  @Test
  public void
      fromRequestParams_withInvalidNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {"invalidNumberOfNights"});

    assertThrows(
        InvalidNumberOfNightsException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeNumberOfNights_shouldThrowInvalidNumberOfNights() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(-1)});

    assertThrows(
        InvalidNumberOfNightsException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withLodgingMode_shouldReturnBedMatcherWithLodgingMode() {
    params.put(LODGING_MODE_PARAM, new String[] {lodgingMode.toString()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(lodgingMode, bedMatcher.getLodgingMode());
  }

  @Test
  public void fromRequestParams_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    params.put(LODGING_MODE_PARAM, new String[] {"invalidLodgingMode"});

    assertThrows(
        InvalidLodgingModeException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withOrigin_shouldMapOrigin() {
    params.put(ORIGIN_PARAM, new String[] {origin.getValue()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(origin, bedMatcher.getOrigin());
  }

  @Test
  public void fromRequestParams_withOriginAndMaxDistance_shouldMapMaxDistance() {
    params.put(ORIGIN_PARAM, new String[] {origin.getValue()});
    params.put(MAX_DISTANCE_PARAM, new String[] {Integer.toString(maxDistance)});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(maxDistance, bedMatcher.getMaxDistance());
  }

  @Test
  public void fromRequestParams_withoutMaxDistance_shouldMapDefaultMaxDistance() {
    params.put(ORIGIN_PARAM, new String[] {origin.getValue()});

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    assertEquals(DEFAULT_MAX_DISTANCE, bedMatcher.getMaxDistance());
  }

  @Test
  public void fromRequestParams_withInvalidMaxDistance_shouldThrowInvalidMaxDistanceException() {
    params.put(MAX_DISTANCE_PARAM, new String[] {"invalidMaxDistance"});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withNegativeMaxDistance_shouldThrowInvalidMaxDistance() {
    params.put(MAX_DISTANCE_PARAM, new String[] {Integer.toString(-1)});

    assertThrows(
        InvalidMaxDistanceException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withoutMinCapacityAndWithArrivalDate_shouldThrowArrivalDateWithoutMinimalCapacityException() {
    params.put(ARRIVAL_DATE_PARAM, new String[] {arrivalDate.getValue().toString()});

    assertThrows(
        ArrivalDateWithoutMinimalCapacityException.class,
        () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withoutMinCapacityAndWithNumberOfNights_shouldThrowNumberOfNightsWithoutMinimalCapacityException() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(numberOfNights)});

    assertThrows(
        NumberOfNightsWithoutMinimalCapacityException.class,
        () -> bedMatcherMapper.fromRequestParams(params));
  }

  @Test
  public void
      fromRequestParams_withoutOriginAndWithMaxDistance_shouldThrowMaxDistanceWithoutOriginException() {
    params.put(MAX_DISTANCE_PARAM, new String[] {Integer.toString(maxDistance)});

    assertThrows(
        MaxDistanceWithoutOriginException.class, () -> bedMatcherMapper.fromRequestParams(params));
  }
}
