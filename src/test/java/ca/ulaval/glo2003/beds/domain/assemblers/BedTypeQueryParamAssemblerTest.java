package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.BedTypeQueryParamAssembler.BED_TYPE_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedType;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedTypeQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private BedTypes bedType = createBedType();
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new BedTypeQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withBedType(bedType)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withoutBedType_shouldReturnSameBuilder() {
    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(queryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withBedType_shouldAssembleBuilder() {
    params.put(BED_TYPE_PARAM, Collections.singletonList(bedType.toString()));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void create_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    params.put(BED_TYPE_PARAM, Collections.singletonList("invalidBedType"));

    assertThrows(
        InvalidBedTypeException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
