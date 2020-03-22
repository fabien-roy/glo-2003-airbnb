package ca.ulaval.glo2003.beds.infrastructure;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedType;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.BedQuery;
import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedTypeFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InMemoryBedQueryBuilderTest {

  private static BedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();

  @BeforeAll
  public static void setUpBuilder() {
    bedQueryBuilder = new InMemoryBedQueryBuilder();
  }

  @Test
  public void withBedType_shouldAddBedTypeFilter() {
    BedQuery bedQuery = bedQueryBuilder.aBedQuery().withBedType(bedType).build();

    assertEquals(1, bedQuery.getFilters().size());
    assertTrue(bedQuery.getFilters().get(0) instanceof InMemoryBedTypeFilter);
    assertEquals(bedType, ((InMemoryBedTypeFilter) bedQuery.getFilters().get(0)).getBedType());
  }
}
