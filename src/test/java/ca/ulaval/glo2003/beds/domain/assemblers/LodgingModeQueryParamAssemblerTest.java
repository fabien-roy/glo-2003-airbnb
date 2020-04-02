package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.LodgingModeQueryParamAssembler.LODGING_MODE_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeObjectMother.createLodgingModeType;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.exceptions.InvalidLodgingModeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LodgingModeQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private LodgingModes lodgingMode = createLodgingModeType();
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new LodgingModeQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withLodgingMode(lodgingMode)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withLodgingMode_shouldAssembleBuilder() {
    params.put(LODGING_MODE_PARAM, Collections.singletonList(lodgingMode.toString()));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void create_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    params.put(LODGING_MODE_PARAM, Collections.singletonList("invalidLodgingMode"));

    assertThrows(
        InvalidLodgingModeException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
