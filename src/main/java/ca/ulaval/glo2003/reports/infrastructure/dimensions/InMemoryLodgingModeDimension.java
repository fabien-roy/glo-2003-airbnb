package ca.ulaval.glo2003.reports.infrastructure.dimensions;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import java.util.Arrays;
import java.util.List;

public class InMemoryLodgingModeDimension extends ReportDimension<LodgingModes> {

  @Override
  protected ReportDimensions getName() {
    return ReportDimensions.LODGING_MODE;
  }

  @Override
  protected List<LodgingModes> getValues() {
    return Arrays.asList(LodgingModes.values());
  }

  protected boolean filter(ReportEvent event, LodgingModes value) {
    return event.getLodgingMode().equals(value);
  }
}
