package ca.ulaval.glo2003.reports.domain.assemblers;

import static ca.ulaval.glo2003.reports.domain.assemblers.DimensionsQueryParamAssembler.DIMENSIONS_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.exceptions.InvalidReportDimensionsException;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DimensionsQueryParamAssemblerTest {

  private static ReportQueryParamAssembler queryAssembler;
  private static ReportQueryBuilder queryBuilder = mock(ReportQueryBuilder.class);
  private static ReportQueryBuilder assembledQueryBuilder = mock(ReportQueryBuilder.class);

  private ReportDimensions dimension = ReportDimensions.PACKAGE;
  private ReportDimensions otherDimension = ReportDimensions.LODGING_MODE;
  private List<ReportDimensions> singleDimension = Collections.singletonList(dimension);
  private List<ReportDimensions> multipleDimensions = Arrays.asList(dimension, otherDimension);
  private List<ReportDimensions> duplicatedDimensions = Arrays.asList(dimension, dimension);
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new DimensionsQueryParamAssembler();
  }

  @Test
  public void assemble_withoutDimensions_shouldReturnSameBuilder() {
    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(queryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withSingleDimension_shouldAssembleBuilder() {
    params.put(DIMENSIONS_PARAM, toParam(singleDimension));
    when(queryBuilder.withDimensions(singleDimension)).thenReturn(assembledQueryBuilder);

    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withMultipleDimensions_shouldAssembleBuilder() {
    params.put(DIMENSIONS_PARAM, toParam(multipleDimensions));
    when(queryBuilder.withDimensions(multipleDimensions)).thenReturn(assembledQueryBuilder);

    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidDimension_shouldThrowInvalidReportDimensionException() {
    params.put(DIMENSIONS_PARAM, Collections.singletonList("invalidDimensions"));

    assertThrows(
        InvalidReportDimensionsException.class,
        () -> queryAssembler.assemble(queryBuilder, params));
  }

  @Test
  public void assemble_withDuplicatedDimensions_shouldThrowInvalidReportDimensionException() {
    params.put(DIMENSIONS_PARAM, toParam(duplicatedDimensions));

    assertThrows(
        InvalidReportDimensionsException.class,
        () -> queryAssembler.assemble(queryBuilder, params));
  }

  private List<String> toParam(List<ReportDimensions> dimensions) {
    return dimensions.stream().map(ReportDimensions::toString).collect(Collectors.toList());
  }
}
