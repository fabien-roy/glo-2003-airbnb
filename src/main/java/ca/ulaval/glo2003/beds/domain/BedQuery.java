package ca.ulaval.glo2003.beds.domain;

import java.util.ArrayList;
import java.util.List;

public class BedQuery {

  private final List<BedFilter> filters;

  public BedQuery(List<BedFilter> filters) {
    this.filters = filters;
  }

  public List<BedFilter> getFilters() {
    return filters;
  }

  public List<Bed> filter(List<Bed> beds) {
    List<Bed> filteredBeds = new ArrayList<>(beds);

    for (BedFilter filter : filters) {
      filteredBeds = filter.filter(filteredBeds);
    }

    return filteredBeds;
  }
}
