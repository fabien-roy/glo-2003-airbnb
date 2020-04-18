package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeObjectMother.createLodgingModeName;
import static ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQueryBuilder.DEFAULT_MAX_DISTANCE;
import static ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQueryBuilder.DEFAULT_NUMBER_OF_NIGHTS;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.ArrivalDateWithoutMinimalCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
import ca.ulaval.glo2003.beds.exceptions.NumberOfNightsWithoutMinimalCapacityException;
import ca.ulaval.glo2003.beds.infrastructure.filters.*;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.time.domain.TimeDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryBuilderTest {

  private static InMemoryBedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private List<BloodTypes> bloodTypes = createBloodTypes();
  private Packages packageName = Packages.BLOODTHIRSTY;
  private int minCapacity = 100;
  private TimeDate arrivalDate = createDate();
  private int numberOfNights = 2;
  private LodgingModes lodgingMode = createLodgingModeName();
  private Location origin = createLocation();
  private double maxDistance = 20d;

  @BeforeAll
  public static void setUpBuilder() {
    bedQueryBuilder = new InMemoryBedQueryBuilder();
  }

  @Test
  public void build_shouldBuildQuery() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.build();

    assertEquals(0, bedQuery.getFilters().size());
  }

  @Test
  public void withBedType_shouldAddBedTypeFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withBedType(bedType).build();

    assertEquals(bedType, ((InMemoryBedTypeFilter) bedQuery.getFilters().get(0)).getBedType());
  }

  @Test
  public void withCleaningFrequency_shouldAddCleaningFrequencyFilter() {
    InMemoryBedQuery bedQuery =
        bedQueryBuilder.aBedQuery().withCleaningFrequency(cleaningFrequency).build();

    assertEquals(
        cleaningFrequency,
        ((InMemoryCleaningFrequencyFilter) bedQuery.getFilters().get(0)).getCleaningFrequency());
  }

  @Test
  public void withBloodTypes_shouldAddBloodTypesFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withBloodTypes(bloodTypes).build();

    assertEquals(
        bloodTypes, ((InMemoryBloodTypesFilter) bedQuery.getFilters().get(0)).getBloodTypes());
  }

  @Test
  public void withPackage_shouldAddPackageFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withPackage(packageName).build();

    assertEquals(packageName, ((InMemoryPackageFilter) bedQuery.getFilters().get(0)).getPackage());
  }

  @Test
  public void withMinCapacity_shouldAddAvailabilityFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withMinCapacity(minCapacity).build();

    assertEquals(
        minCapacity, ((InMemoryAvailabilityFilter) bedQuery.getFilters().get(0)).getMinCapacity());
  }

  @Test
  public void withArrivalDate_shouldAddAvailabilityFilter() {
    InMemoryBedQuery bedQuery =
        bedQueryBuilder
            .aBedQuery()
            .withMinCapacity(minCapacity)
            .withArrivalDate(arrivalDate)
            .build();

    assertEquals(
        arrivalDate, ((InMemoryAvailabilityFilter) bedQuery.getFilters().get(0)).getArrivalDate());
  }

  @Test
  public void withoutArrivalDate_shouldSetArrivalDateToNow() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withMinCapacity(minCapacity).build();

    assertEquals(
        TimeDate.now(),
        ((InMemoryAvailabilityFilter) bedQuery.getFilters().get(0)).getArrivalDate());
  }

  @Test
  public void
      withArrivalDateAndWithoutMinCapacity_shouldThrowArrivalDateWithoutMinimalCapacityException() {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery().withArrivalDate(arrivalDate);

    assertThrows(ArrivalDateWithoutMinimalCapacityException.class, builder::build);
  }

  @Test
  public void withNumberOfNights_shouldAddAvailabilityFilter() {
    InMemoryBedQuery bedQuery =
        bedQueryBuilder
            .aBedQuery()
            .withMinCapacity(minCapacity)
            .withNumberOfNights(numberOfNights)
            .build();

    assertEquals(
        numberOfNights,
        ((InMemoryAvailabilityFilter) bedQuery.getFilters().get(0)).getNumberOfNights());
  }

  @Test
  public void withoutNumberOfNights_shouldSetDefaultNumberOfNights() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withMinCapacity(minCapacity).build();

    assertEquals(
        DEFAULT_NUMBER_OF_NIGHTS,
        ((InMemoryAvailabilityFilter) bedQuery.getFilters().get(0)).getNumberOfNights());
  }

  @Test
  public void
      withNumberOfNightsAndWithoutMinCapacity_shouldThrowNumberOfNightsWithoutMinimalCapacityException() {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery().withNumberOfNights(numberOfNights);

    assertThrows(NumberOfNightsWithoutMinimalCapacityException.class, builder::build);
  }

  @Test
  public void withLodgingMode_shouldAddLodgingModeFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withLodgingMode(lodgingMode).build();

    assertEquals(
        lodgingMode, ((InMemoryLodgingModeFilter) bedQuery.getFilters().get(0)).getLodgingMode());
  }

  @Test
  public void withOrigin_shouldAddDistanceFilter() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withOrigin(origin).build();

    assertEquals(origin, ((InMemoryDistanceFilter) bedQuery.getFilters().get(0)).getOrigin());
  }

  @Test
  public void withMaxDistance_shouldAddDistanceFilter() {
    InMemoryBedQuery bedQuery =
        bedQueryBuilder.aBedQuery().withOrigin(origin).withMaxDistance(maxDistance).build();

    assertEquals(
        maxDistance, ((InMemoryDistanceFilter) bedQuery.getFilters().get(0)).getMaxDistance());
  }

  @Test
  public void withoutMaxDistance_shouldSetMaxDistanceToDefault() {
    InMemoryBedQuery bedQuery = bedQueryBuilder.aBedQuery().withOrigin(origin).build();

    assertEquals(
        DEFAULT_MAX_DISTANCE,
        ((InMemoryDistanceFilter) bedQuery.getFilters().get(0)).getMaxDistance());
  }

  @Test
  public void withMaxDistanceAndWithoutOrigin_shouldThrowMaxDistanceWithoutOriginException() {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery().withMaxDistance(maxDistance);

    assertThrows(MaxDistanceWithoutOriginException.class, builder::build);
  }
}
