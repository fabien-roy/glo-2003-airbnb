package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPackageFilter implements BedFilter {

  private final Packages packageName;

  public InMemoryPackageFilter(Packages packageName) {
    this.packageName = packageName;
  }

  public Packages getPackage() {
    return packageName;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.isPackageAvailable(packageName))
        .collect(Collectors.toList());
  }
}
