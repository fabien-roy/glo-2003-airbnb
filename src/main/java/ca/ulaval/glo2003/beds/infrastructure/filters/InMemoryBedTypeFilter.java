package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBedTypeFilter implements BedFilter {

  private final BedTypes bedType;

  public InMemoryBedTypeFilter(BedTypes bedType) {
    this.bedType = bedType;
  }

  public BedTypes getBedType() {
    return bedType;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.getBedType().equals(bedType))
        .collect(Collectors.toList());
  }
}
