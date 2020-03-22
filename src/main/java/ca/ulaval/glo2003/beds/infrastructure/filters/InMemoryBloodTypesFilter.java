package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBloodTypesFilter implements BedFilter {

  private final List<BloodTypes> bloodTypes;

  public InMemoryBloodTypesFilter(List<BloodTypes> bloodTypes) {
    this.bloodTypes = bloodTypes;
  }

  public List<BloodTypes> getBloodTypes() {
    return bloodTypes;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.getBloodTypes().containsAll(bloodTypes))
        .collect(Collectors.toList());
  }
}
