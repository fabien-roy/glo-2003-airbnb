package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test PackageReportDimension
public class PackageReportDimension extends ReportDimension<Packages> {

  @Override
  public ReportDimensions getDimensionName() {
    return ReportDimensions.PACKAGE;
  }

  @Override
  public List<ReportPeriodData> split(ReportPeriodData data) {
    List<ReportPeriodData> splitData = new ArrayList<>();
    Arrays.asList(Packages.values()).forEach(value -> splitData.add(filter(data, value)));
    return splitData;
  }

  private ReportPeriodData filter(ReportPeriodData data, Packages packageName) {
    List<Transaction> splitTransactions =
        data.getTransactions().stream()
            .filter(transaction -> transaction.getBooking().getPackage().equals(packageName))
            .collect(Collectors.toList());
    ReportPeriodData filteredData = new ReportPeriodData(splitTransactions);
    filteredData.addDimension(toDimensionData(packageName));
    return filteredData;
  }
}
