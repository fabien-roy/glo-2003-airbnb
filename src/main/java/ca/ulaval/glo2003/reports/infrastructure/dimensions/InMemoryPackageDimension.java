package ca.ulaval.glo2003.reports.infrastructure.dimensions;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import java.util.Arrays;
import java.util.List;

public class InMemoryPackageDimension extends ReportDimension<Packages> {

  @Override
  protected ReportDimensions getName() {
    return ReportDimensions.PACKAGE;
  }

  @Override
  public List<Packages> getValues() {
    return Arrays.asList(Packages.values());
  }

  protected boolean filter(ReportEvent event, Packages value) {
    return event.getPackage().equals(value);
  }
}
