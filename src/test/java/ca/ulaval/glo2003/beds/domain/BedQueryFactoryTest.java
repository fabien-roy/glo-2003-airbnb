package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.BedQueryFactory.NUMBER_OF_NIGHTS_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.BLOOD_TYPES_PARAM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.mappers.BookingDateMapper;
import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryFactoryTest {

  private static BedQueryFactory bedQueryFactory;
  private static BedQueryBuilder bedQueryBuilder;
  private static BookingDateMapper bookingDateMapper;

  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private BloodTypes bloodType = BloodTypes.O_MINUS;
  private BloodTypes otherBloodType = BloodTypes.AB_MINUS;
  private Packages packageName = Packages.BLOODTHIRSTY;
  private int capacity = BedTypesCapacities.get(bedType);
  private BookingDate arrivalDate =
      new BookingDate(LocalDate.now()); // TODO : Use default constructor
  private int numberOfNights = 2;
  private BedQuery query;
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpFactory() {
    bedQueryBuilder = mock(BedQueryBuilder.class);
    bookingDateMapper = mock(BookingDateMapper.class);
    bedQueryFactory = new BedQueryFactory(bedQueryBuilder, bookingDateMapper);
  }

  @BeforeEach
  public void setUpMocks() {
    when(bedQueryBuilder.aBedQuery()).thenReturn(bedQueryBuilder);
    when(bedQueryBuilder.build()).thenReturn(query);
    when(bookingDateMapper.fromString(arrivalDate.getValue().toString())).thenReturn(arrivalDate);
  }

  @Test
  public void create_shouldCreateQuery() {
    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withBedType_shouldCreateFilteredQuery() {
    params.put(BED_TYPE_PARAM, new String[] {bedType.toString()});
    when(bedQueryBuilder.withBedType(bedType)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    params.put(BED_TYPE_PARAM, new String[] {"invalidBedType"});

    assertThrows(InvalidBedTypeException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withCleaningFrequency_shouldCreateFilteredQuery() {
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {cleaningFrequency.toString()});
    when(bedQueryBuilder.withCleaningFrequency(cleaningFrequency)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    params.put(CLEANING_FREQUENCY_PARAM, new String[] {"invalidCleaningFrequency"});

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withSingleBloodType_shouldCreateFilteredQuery() {
    List<BloodTypes> bloodTypes = Collections.singletonList(bloodType);
    params.put(BLOOD_TYPES_PARAM, new String[] {bloodType.toString()});
    when(bedQueryBuilder.withBloodTypes(bloodTypes)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withMultipleBloodTypes_shouldCreateFilteredQuery() {
    List<BloodTypes> bloodTypes = Arrays.asList(bloodType, otherBloodType);
    params.put(BLOOD_TYPES_PARAM, new String[] {bloodType.toString(), otherBloodType.toString()});
    when(bedQueryBuilder.withBloodTypes(bloodTypes)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withInvalidBloodTypes_shouldThrowInvalidBloodTypeException() {
    params.put(BLOOD_TYPES_PARAM, new String[] {"invalidBloodTypes"});

    assertThrows(InvalidBloodTypesException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withPackage_shouldCreateFilteredQuery() {
    params.put(PACKAGE_NAME_PARAM, new String[] {packageName.toString()});
    when(bedQueryBuilder.withPackage(packageName)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withInvalidPackage_shouldThrowInvalidPackageException() {
    params.put(PACKAGE_NAME_PARAM, new String[] {"invalidPackage"});

    assertThrows(InvalidPackageException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withMinCapacity_shouldCreateFilteredQuery() {
    when(bedQueryBuilder.withMinCapacity(capacity)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withNegativeMinCapacity_shouldThrowInvalidCapacityException() {
    int invalidCapacity = -1;
    params.put(MIN_CAPACITY_PARAM, new String[] {Integer.toString(invalidCapacity)});

    assertThrows(InvalidCapacityException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withInvalidMinCapacity_shouldThrowInvalidCapacityException() {
    params.put(MIN_CAPACITY_PARAM, new String[] {"invalidCapacity"});

    assertThrows(InvalidCapacityException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withArrivalDate_shouldCreateFilteredQuery() {
    params.put(ARRIVAL_DATE_PARAM, new String[] {arrivalDate.getValue().toString()});
    when(bedQueryBuilder.withArrivalDate(arrivalDate)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withNumberOfNights_shouldCreateFilteredQuery() {
    when(bedQueryBuilder.withNumberOfNights(numberOfNights)).thenReturn(bedQueryBuilder);

    BedQuery actualQuery = bedQueryFactory.create(params);

    assertSame(query, actualQuery);
  }

  @Test
  public void create_withNegativeNumberOfNights_shouldThrowNumberOfNightsException() {
    int invalidNumberOfNights = -1;
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(invalidNumberOfNights)});

    assertThrows(InvalidNumberOfNightsException.class, () -> bedQueryFactory.create(params));
  }

  @Test
  public void create_withInvalidNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {"invalidNumberOfNights"});

    assertThrows(InvalidNumberOfNightsException.class, () -> bedQueryFactory.create(params));
  }
}
