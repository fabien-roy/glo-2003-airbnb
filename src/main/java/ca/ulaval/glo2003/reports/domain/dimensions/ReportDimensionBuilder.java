package ca.ulaval.glo2003.reports.domain.dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test ReportDimensionBuilder
public class ReportDimensionBuilder {

  private List<ReportDimensions> dimensionTypes = new ArrayList<>();

  public ReportDimensionBuilder someDimensions() {
    return new ReportDimensionBuilder();
  }

  public ReportDimensionBuilder withTypes(List<ReportDimensions> dimensionTypes) {
    this.dimensionTypes = dimensionTypes;
    return this;
  }

  public List<ReportDimension> buildMany() {
    return dimensionTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportDimension buildOne(ReportDimensions metricType) {
    switch (metricType) {
      case PACKAGE:
        return new PackageReportDimension();
      default:
      case LODGING_MODE:
        return new LodgingModeReportDimension();
    }
  }
}
