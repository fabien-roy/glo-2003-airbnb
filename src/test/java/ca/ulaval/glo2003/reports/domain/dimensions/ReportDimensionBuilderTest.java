package ca.ulaval.glo2003.reports.domain.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

  @Test
  public void buildMany_withPackageDimension_shouldBuildPackageDimension() {
    List<ReportDimensions> dimensionTypes = Collections.singletonList(ReportDimensions.PACKAGE);

    List<ReportDimension> dimensions =
        dimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertEquals(1, dimensions.size());
    assertTrue(dimensions.get(0) instanceof PackageDimension);
  }

  @Test
  public void buildMany_withLodgingModeDimension_shouldBuildLodgingModeDimension() {
    List<ReportDimensions> dimensionTypes =
        Collections.singletonList(ReportDimensions.LODGING_MODE);

    List<ReportDimension> dimensions =
        dimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();

    assertEquals(1, dimensions.size());
    assertTrue(dimensions.get(0) instanceof LodgingModeDimension);
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
