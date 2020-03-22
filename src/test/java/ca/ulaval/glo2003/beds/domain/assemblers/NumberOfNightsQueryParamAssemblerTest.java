package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.NumberOfNightsQueryParamAssembler.NUMBER_OF_NIGHTS_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumberOfNightsQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private int numberOfNights = 2;
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new NumberOfNightsQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withNumberOfNights(numberOfNights)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withNumberOfNights_shouldAssembleBuilder() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(numberOfNights)});

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void create_withNegativeNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {Integer.toString(-1)});

    assertThrows(
        InvalidNumberOfNightsException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  @Test
  public void create_withInvalidNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    params.put(NUMBER_OF_NIGHTS_PARAM, new String[] {"invalidNumberOfNights"});

    assertThrows(
        InvalidNumberOfNightsException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
