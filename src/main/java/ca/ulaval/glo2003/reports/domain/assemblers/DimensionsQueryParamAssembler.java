package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

// TODO : Test DimensionsQueryParamAssembler
public class DimensionsQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String DIMENSIONS_PARAM = "dimensions";
  private final ReportDimensionBuilder reportDimensionBuilder;

  @Inject
  public DimensionsQueryParamAssembler(ReportDimensionBuilder reportDimensionBuilder) {
    this.reportDimensionBuilder = reportDimensionBuilder;
  }

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> dimensions = params.get(DIMENSIONS_PARAM);
    List<ReportDimension> reportDimensions;

    if (dimensions != null) {
      List<ReportDimensions> dimensionTypes =
          dimensions.stream().map(ReportDimensions::get).collect(Collectors.toList());
      reportDimensions =
          reportDimensionBuilder.someDimensions().withTypes(dimensionTypes).buildMany();
    } else {
      reportDimensions = reportDimensionBuilder.someDimensions().buildMany();
    }

    return builder.withDimensions(reportDimensions);
  }
}
