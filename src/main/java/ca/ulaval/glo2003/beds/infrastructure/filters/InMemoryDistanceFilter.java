package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import java.util.List;

public class InMemoryDistanceFilter implements InMemoryBedFilter {

  private final ZipCode origin;
  private final int maxDistance;

  public InMemoryDistanceFilter(ZipCode origin, int maxDistance) {
    this.origin = origin;
    this.maxDistance = maxDistance;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds; // TODO
  }

  public ZipCode getOrigin() {
    return origin;
  }

  public int getMaxDistance() {
    return maxDistance;
  }
}
