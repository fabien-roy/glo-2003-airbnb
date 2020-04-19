package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DimensionsQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String DIMENSIONS_PARAM = "dimensions";

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> dimensions = params.get(DIMENSIONS_PARAM);

    return dimensions != null ? builder.withDimensions(parseDimensions(dimensions)) : builder;
  }

  private List<ReportDimensions> parseDimensions(List<String> dimensions) {
    return dimensions.stream().map(ReportDimensions::get).collect(Collectors.toList());
  }
}
