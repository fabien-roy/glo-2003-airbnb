package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBedTypeFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryBloodTypesFilter;
import ca.ulaval.glo2003.beds.infrastructure.filters.InMemoryCleaningFrequencyFilter;
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
  public InMemoryBedQueryBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency) {
    this.filters.add(new InMemoryCleaningFrequencyFilter(cleaningFrequency));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withBloodTypes(List<BloodTypes> bloodTypes) {
    this.filters.add(new InMemoryBloodTypesFilter(bloodTypes));
    return this;
  }

  @Override
  public BedQuery build() {
    return new BedQuery(filters);
  }
}
