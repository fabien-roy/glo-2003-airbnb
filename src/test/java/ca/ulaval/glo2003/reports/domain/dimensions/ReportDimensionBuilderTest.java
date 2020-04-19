package ca.ulaval.glo2003.reports.domain.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReportDimensionBuilderTest {

  private static ReportDimensionBuilder dimensionBuilder;

  @BeforeAll
  public static void setUpBuilder() {
    dimensionBuilder = new ReportDimensionBuilder();
  }

  @Test
  public void buildMany_withoutDimensions_shouldBuildNoDimension() {
    List<ReportDimension> dimensions = dimensionBuilder.someDimensions().buildMany();

    assertEquals(0, dimensions.size());
  }

  @ParameterizedTest
  @MethodSource("provideDimensions")
  public void buildMany_withDimension_shouldBuildDimension(
      ReportDimensions dimensionType, Class<? extends ReportDimensions> dimensionClass) {
    List<ReportDimensions> dimensionTypes = Collections.singletonList(dimensionType);

    List<ReportDimension> dimensions =
        dimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertEquals(1, dimensions.size());
    assertTrue(dimensionClass.isInstance(dimensions.get(0)));
  }

  private static Stream<Arguments> provideDimensions() {
    return Stream.of(
        Arguments.of(ReportDimensions.PACKAGE, PackageDimension.class),
        Arguments.of(ReportDimensions.LODGING_MODE, LodgingModeDimension.class));
  }

  @Test
  public void buildMany_withMultipleDimensions_shouldBuildDimensions() {
    List<ReportDimensions> dimensionTypes =
        Arrays.asList(ReportDimensions.PACKAGE, ReportDimensions.LODGING_MODE);

    List<ReportDimension> dimensions =
        dimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertEquals(2, dimensions.size());
    assertTrue(dimensions.stream().anyMatch(dimension -> dimension instanceof PackageDimension));
    assertTrue(
        dimensions.stream().anyMatch(dimension -> dimension instanceof LodgingModeDimension));
  }
}
