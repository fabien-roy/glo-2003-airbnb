package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedTypeFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBloodTypesFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryCleaningFrequencyFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryPackageFilter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryBuilderTest {

  private BedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private List<BloodTypes> bloodTypes = createBloodTypes();
  private Packages packageName = Packages.BLOODTHIRSTY;

  @BeforeEach
  public void setUpBuilder() {
    bedQueryBuilder = new InMemoryBedQueryBuilder();
  }

  @Test
  public void build_shouldBuildQuery() {
    BedQuery bedQuery = bedQueryBuilder.build();

    assertEquals(0, bedQuery.getFilters().size());
  }

  @Test
  public void withBedType_shouldAddBedTypeFilter() {
    BedQuery bedQuery = bedQueryBuilder.aBedQuery().withBedType(bedType).build();

    assertEquals(bedType, ((InMemoryBedTypeFilter) bedQuery.getFilters().get(0)).getBedType());
  }

  @Test
  public void withCleaningFrequency_shouldAddCleaningFrequencyFilter() {
    BedQuery bedQuery =
        bedQueryBuilder.aBedQuery().withCleaningFrequency(cleaningFrequency).build();

    assertEquals(
        cleaningFrequency,
        ((InMemoryCleaningFrequencyFilter) bedQuery.getFilters().get(0)).getCleaningFrequency());
  }

  @Test
  public void withBloodTypes_shouldAddBloodTypesFilter() {
    BedQuery bedQuery = bedQueryBuilder.aBedQuery().withBloodTypes(bloodTypes).build();

    assertEquals(
        bloodTypes, ((InMemoryBloodTypesFilter) bedQuery.getFilters().get(0)).getBloodTypes());
  }

  @Test
  public void withPackage_shouldAddPackageFilter() {
    BedQuery bedQuery = bedQueryBuilder.aBedQuery().withPackage(packageName).build();

    assertEquals(packageName, ((InMemoryPackageFilter) bedQuery.getFilters().get(0)).getPackage());
  }
}
