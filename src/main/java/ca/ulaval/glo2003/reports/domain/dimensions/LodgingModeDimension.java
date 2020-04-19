package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.Arrays;
import java.util.List;

// TODO : Test LodgingModeReportDimension
public class LodgingModeDimension extends ReportDimension<LodgingModes> {

  @Override
  public ReportDimensions getName() {
    return ReportDimensions.LODGING_MODE;
  }

  @Override
  public List<LodgingModes> getValues() {
    return Arrays.asList(LodgingModes.values());
  }

  protected boolean filter(Transaction transaction, LodgingModes value) {
    return transaction.getBed().getLodgingMode().getName().equals(value);
  }
}
