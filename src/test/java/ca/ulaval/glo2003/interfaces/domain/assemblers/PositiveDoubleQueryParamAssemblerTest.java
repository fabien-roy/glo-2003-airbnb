package ca.ulaval.glo2003.interfaces.domain.assemblers;

import static ca.ulaval.glo2003.interfaces.domain.assemblers.FakePositiveDoubleQueryParamAssembler.POSITIVE_DOUBLE_PARAM;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;
import ca.ulaval.glo2003.errors.exceptions.TestingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PositiveDoubleQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);

  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new FakePositiveDoubleQueryParamAssembler();
  }

  @Test
  public void assemble_withInvalidValue_shouldThrowException() {
    params.put(POSITIVE_DOUBLE_PARAM, Collections.singletonList("invalidValue"));

    assertThrows(TestingException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  @Test
  public void assemble_withNegativeValue_shouldThrowException() {
    params.put(POSITIVE_DOUBLE_PARAM, Collections.singletonList("-1"));

    assertThrows(TestingException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  @Test
  public void assemble_withNullValue_shouldThrowException() {
    params.put(POSITIVE_DOUBLE_PARAM, Collections.singletonList("0"));

    assertThrows(TestingException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
