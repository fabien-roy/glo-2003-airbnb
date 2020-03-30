package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.MinimalCapacityQueryParamAssembler.MIN_CAPACITY_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.exceptions.InvalidMinimalCapacityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinimalCapacityQueryParamAssemblerTest {

  private static PositiveIntegerQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private int minCapacity = 100;
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new MinimalCapacityQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withMinCapacity(minCapacity)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withMinCapacity_shouldAssembleBuilder() {
    params.put(MIN_CAPACITY_PARAM, Collections.singletonList(Integer.toString(minCapacity)));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void throwException_shouldThrowInvalidMinimalCapacityException() {
    assertThrows(InvalidMinimalCapacityException.class, () -> queryAssembler.throwException());
  }
}
