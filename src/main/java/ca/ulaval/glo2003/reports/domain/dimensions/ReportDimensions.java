package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportDimensionsException;
import java.util.HashMap;
import java.util.Map;

public enum ReportDimensions {
  PACKAGE("package"),
  LODGING_MODE("lodgingMode");

  private String dimension;
  private static final Map<String, ReportDimensions> lookup = new HashMap<>();

  static {
    for (ReportDimensions dimension : ReportDimensions.values()) {
      lookup.put(dimension.toString(), dimension);
    }
  }

  ReportDimensions(String dimension) {
    this.dimension = dimension;
  }

  @Override
  public String toString() {
    return dimension;
  }

  public static ReportDimensions get(String dimension) {
    ReportDimensions foundDimension = lookup.get(dimension);

    if (foundDimension == null) throw new InvalidReportDimensionsException();

    return foundDimension;
  }
}
