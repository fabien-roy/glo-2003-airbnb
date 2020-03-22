package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.beds.domain.BedQuery;
import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedTypeFilter;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBedQueryBuilder implements BedQueryBuilder {

  List<BedFilter> filters = new ArrayList<>();

  @Override
  public BedQueryBuilder aBedQuery() {
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withBedType(BedTypes bedType) {
    this.filters.add(new InMemoryBedTypeFilter(bedType));
    return this;
  }

  @Override
  public BedQuery build() {
    return new BedQuery(filters);
  }
}
