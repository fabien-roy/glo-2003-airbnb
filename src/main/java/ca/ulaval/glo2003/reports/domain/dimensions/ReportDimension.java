package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReportDimension<T> {

  protected abstract ReportDimensions getName();

  protected abstract List<T> getValues();

  protected abstract boolean filter(Transaction transaction, T value);

  public List<ReportPeriodData> splitAll(List<ReportPeriodData> data) {
    List<ReportPeriodData> splitData = new ArrayList<>();
    data.forEach(datum -> splitData.addAll(split(datum)));
    return splitData;
  }

  private List<ReportPeriodData> split(ReportPeriodData data) {
    List<ReportPeriodData> splitData = new ArrayList<>();
    getValues().forEach(value -> splitData.add(filterAll(data, value)));
    return splitData;
  }

  protected ReportPeriodData filterAll(ReportPeriodData data, T value) {
    List<Transaction> splitTransactions =
        data.getTransactions().stream()
            .filter(transaction -> filter(transaction, value))
            .collect(Collectors.toList());
    ReportPeriodData filteredData = new ReportPeriodData(splitTransactions);
    filteredData.addDimension(toDimensionData(value));
    return filteredData;
  }

  protected ReportDimensionData<T> toDimensionData(T value) {
    return new ReportDimensionData<T>() {
      @Override
      public ReportDimensions getName() {
        return ReportDimension.this.getName();
      }

      @Override
      public T getValue() {
        return value;
      }
    };
  }
}
