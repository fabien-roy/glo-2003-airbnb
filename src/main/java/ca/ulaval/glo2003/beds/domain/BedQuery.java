package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public class BedQuery {

  private final List<BedFilter> filters;

  public BedQuery(List<BedFilter> filters) {
    this.filters = filters;
  }

  public void filter(List<Bed> beds) {
    filters.forEach(bedQuery -> bedQuery.filter(beds));
  }
}
