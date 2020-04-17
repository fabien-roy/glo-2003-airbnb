package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import java.util.ArrayList;
import java.util.List;

// TODO : Test ReportDimension
public abstract class ReportDimension<T> {

  public abstract ReportDimensions getDimensionName();

  public abstract List<ReportPeriodData> split(ReportPeriodData data);

  public List<ReportPeriodData> splitAll(List<ReportPeriodData> data) {
    List<ReportPeriodData> splitData = new ArrayList<>();
    data.forEach(datum -> splitData.addAll(split(datum)));
    return splitData;
  }

  protected ReportDimensionData<T> toDimensionData(T value) {
    return new ReportDimensionData<T>() {
      @Override
      public ReportDimensions getName() {
        return getDimensionName();
      }

      @Override
      public T getValue() {
        return value;
      }
    };
  }
}
