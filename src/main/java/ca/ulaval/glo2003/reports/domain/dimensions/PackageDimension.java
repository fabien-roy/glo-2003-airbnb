package ca.ulaval.glo2003.reports.domain.dimensions;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.Arrays;
import java.util.List;

public class PackageDimension extends ReportDimension<Packages> {

  @Override
  protected ReportDimensions getName() {
    return ReportDimensions.PACKAGE;
  }

  @Override
  public List<Packages> getValues() {
    return Arrays.asList(Packages.values());
  }

  protected boolean filter(Transaction transaction, Packages value) {
    return transaction.getBooking().getPackage().equals(value);
  }
}
