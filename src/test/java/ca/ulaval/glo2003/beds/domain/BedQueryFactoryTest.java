package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedType;
import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.BED_TYPE_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedQueryFactoryTest {

  private static BedQueryFactory bedQueryFactory;
  private static BedQueryBuilder bedQueryBuilder;

  private BedTypes bedType = createBedType();
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
  public void create_withInvalidBedType_shouldCreateFilteredQuery() {
    params.put(BED_TYPE_PARAM, new String[] {"invalidBedType"});

    assertThrows(InvalidBedTypeException.class, () -> bedQueryFactory.create(params));
  }
}
