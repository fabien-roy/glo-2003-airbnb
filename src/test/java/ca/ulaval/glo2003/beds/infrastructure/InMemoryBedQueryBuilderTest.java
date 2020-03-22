package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedType;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCleaningFrequency;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.BedQuery;
import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedTypeFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryCleaningFrequencyFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryBuilderTest {

  private BedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();
  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();

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
}
