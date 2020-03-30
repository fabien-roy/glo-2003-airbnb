package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.MaximumDistanceQueryParamAssembler.MAX_DISTANCE_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaximumDistanceQueryParamAssemblerTest {

  private static PositiveIntegerQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private int maxDistance = 30;
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new MaximumDistanceQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withMaxDistance(maxDistance)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withMaxDistance_shouldAssembleBuilder() {
    params.put(MAX_DISTANCE_PARAM, Collections.singletonList(Integer.toString(maxDistance)));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void throwException_shouldThrowInvalidMaxDistanceException() {
    assertThrows(InvalidMaxDistanceException.class, () -> queryAssembler.throwException());
  }
}
