package ca.ulaval.glo2003.reports.domain.dimensions;

public interface ReportDimensionData<T> {

  ReportDimensions getName();

  T getValue();
}
