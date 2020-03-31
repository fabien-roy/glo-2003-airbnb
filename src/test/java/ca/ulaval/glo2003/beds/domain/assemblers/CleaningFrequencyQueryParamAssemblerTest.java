package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.CleaningFrequencyQueryParamAssembler.CLEANING_FREQUENCY_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCleaningFrequency;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CleaningFrequencyQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private CleaningFrequencies cleaningFrequency = createCleaningFrequency();
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new CleaningFrequencyQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withCleaningFrequency(cleaningFrequency)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withCleaningFrequency_shouldAssembleBuilder() {
    params.put(CLEANING_FREQUENCY_PARAM, Collections.singletonList(cleaningFrequency.toString()));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    params.put(CLEANING_FREQUENCY_PARAM, Collections.singletonList("invalidCleaningFrequency"));

    assertThrows(
        InvalidCleaningFrequencyException.class,
        () -> queryAssembler.assemble(queryBuilder, params));
  }
}
