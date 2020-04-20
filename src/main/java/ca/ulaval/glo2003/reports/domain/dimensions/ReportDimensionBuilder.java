package ca.ulaval.glo2003.reports.domain.dimensions;

import java.util.List;

public interface ReportDimensionBuilder {

  ReportDimensionBuilder someDimensions();

  ReportDimensionBuilder withTypes(List<ReportDimensions> dimensionTypes);

  List<ReportDimension> buildMany();
}
