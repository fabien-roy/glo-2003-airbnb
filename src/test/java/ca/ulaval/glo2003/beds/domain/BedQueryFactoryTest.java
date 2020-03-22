package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.BLOOD_TYPES_PARAM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryFactoryTest {

  private static BedQueryFactory bedQueryFactory;
  private static BedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private BloodTypes bloodType = BloodTypes.O_MINUS;
  private BloodTypes otherBloodType = BloodTypes.AB_MINUS;
  private BedQuery query;
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpFactory() {
    bedQueryBuilder = mock(BedQueryBuilder.class);
    bedQueryFactory = new BedQueryFactory(bedQueryBuilder);
  }

  @BeforeEach
  public void setUpMocks() {
    when(bedQueryBuilder.aBedQuery()).thenReturn(bedQueryBuilder);
    when(bedQueryBuilder.build()).thenReturn(query);
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
}
