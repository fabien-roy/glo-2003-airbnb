package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryLodgingModeFilter implements InMemoryBedFilter {

  private final LodgingModes lodgingMode;

  public InMemoryLodgingModeFilter(LodgingModes lodgingMode) {
    this.lodgingMode = lodgingMode;
  }

  public LodgingModes getLodgingMode() {
    return lodgingMode;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.getLodgingMode().getName().equals(lodgingMode))
        .collect(Collectors.toList());
  }
}
