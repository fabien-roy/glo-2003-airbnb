package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.locations.domain.Location;
import java.util.List;

public class InMemoryDistanceFilter implements InMemoryBedFilter {

  private final Location origin;
  private final int maxDistance;

  public InMemoryDistanceFilter(Location origin, int maxDistance) {
    this.origin = origin;
    this.maxDistance = maxDistance;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds; // TODO
  }

  public Location getOrigin() {
    return origin;
  }

  public int getMaxDistance() {
    return maxDistance;
  }
}
