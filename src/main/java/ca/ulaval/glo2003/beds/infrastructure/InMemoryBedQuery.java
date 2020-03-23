package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedQuery;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedFilter;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBedQuery implements BedQuery {

  private final List<InMemoryBedFilter> filters;
  private List<Bed> beds;

  public InMemoryBedQuery(List<InMemoryBedFilter> filters) {
    this.filters = filters;
  }

  public List<InMemoryBedFilter> getFilters() {
    return filters;
  }

  public void setBeds(List<Bed> beds) {
    this.beds = beds;
  }

  @Override
  public List<Bed> execute() {
    return filter(beds);
  }

  private List<Bed> filter(List<Bed> beds) {
    List<Bed> filteredBeds = new ArrayList<>(beds);

    for (InMemoryBedFilter filter : filters) filteredBeds = filter.filter(filteredBeds);

    return filteredBeds;
  }
}
