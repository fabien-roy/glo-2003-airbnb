package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test LodgingModeReportDimension
public class LodgingModeReportDimension extends ReportDimension<LodgingModes> {

  @Override
  public ReportDimensions getDimensionName() {
    return ReportDimensions.LODGING_MODE;
  }

  @Override
  public List<ReportPeriodData> split(ReportPeriodData data) {
    List<ReportPeriodData> splitData = new ArrayList<>();
    Arrays.asList(LodgingModes.values()).forEach(value -> splitData.add(filter(data, value)));
    return splitData;
  }

  private ReportPeriodData filter(ReportPeriodData data, LodgingModes lodgingMode) {
    List<Transaction> splitTransactions =
        data.getTransactions().stream()
            .filter(
                transaction -> transaction.getBed().getLodgingMode().getName().equals(lodgingMode))
            .collect(Collectors.toList());
    ReportPeriodData filteredData = new ReportPeriodData(splitTransactions);
    filteredData.addDimension(toDimensionData(lodgingMode));
    return filteredData;
  }
}
