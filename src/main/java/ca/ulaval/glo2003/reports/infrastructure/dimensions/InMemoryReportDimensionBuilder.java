package ca.ulaval.glo2003.reports.infrastructure.dimensions;

import ca.ulaval.glo2003.reports.domain.dimensions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportDimensionBuilder implements ReportDimensionBuilder {

  private List<ReportDimensions> dimensionTypes = new ArrayList<>();

  public InMemoryReportDimensionBuilder someDimensions() {
    return new InMemoryReportDimensionBuilder();
  }

  public InMemoryReportDimensionBuilder withTypes(List<ReportDimensions> dimensionTypes) {
    this.dimensionTypes = dimensionTypes;
    return this;
  }

  public List<ReportDimension> buildMany() {
    return dimensionTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportDimension buildOne(ReportDimensions metricType) {
    switch (metricType) {
      case PACKAGE:
        return new InMemoryPackageDimension();
      default:
      case LODGING_MODE:
        return new InMemoryLodgingModeDimension();
    }
  }
}
