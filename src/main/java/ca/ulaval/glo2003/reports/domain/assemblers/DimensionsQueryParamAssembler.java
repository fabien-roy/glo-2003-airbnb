package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.exceptions.InvalidReportDimensionsException;
import java.util.HashSet;
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
    validateNotDuplicated(dimensions);
    return dimensions.stream().map(ReportDimensions::get).collect(Collectors.toList());
  }

  private void validateNotDuplicated(List<String> dimensions) {
    if (new HashSet<>(dimensions).size() != dimensions.size())
      throw new InvalidReportDimensionsException();
  }
}
