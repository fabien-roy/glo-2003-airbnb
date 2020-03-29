package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.BloodTypesQueryParamAssembler.BLOOD_TYPES_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BloodTypesQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private BloodTypes bloodType = BloodTypes.O_MINUS;
  private BloodTypes otherBloodType = BloodTypes.AB_MINUS;
  private List<BloodTypes> singleBloodType = Collections.singletonList(bloodType);
  private List<BloodTypes> multipleBloodTypes = Arrays.asList(bloodType, otherBloodType);
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new BloodTypesQueryParamAssembler();
  }

  @Test
  public void assemble_withSingleBloodType_shouldAssembleBuilder() {
    params.put(BLOOD_TYPES_PARAM, toParam(singleBloodType));
    when(queryBuilder.withBloodTypes(singleBloodType)).thenReturn(assembledQueryBuilder);

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withMultipleBloodTypes_shouldAssembleBuilder() {
    params.put(BLOOD_TYPES_PARAM, toParam(multipleBloodTypes));
    when(queryBuilder.withBloodTypes(multipleBloodTypes)).thenReturn(assembledQueryBuilder);

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidBloodTypes_shouldThrowInvalidBloodTypeException() {
    params.put(BLOOD_TYPES_PARAM, Collections.singletonList("invalidBloodTypes"));

    assertThrows(
        InvalidBloodTypesException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  private List<String> toParam(List<BloodTypes> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::toString).collect(Collectors.toList());
  }
}
