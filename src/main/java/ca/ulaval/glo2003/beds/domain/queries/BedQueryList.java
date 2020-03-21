package ca.ulaval.glo2003.beds.domain.queries;

import ca.ulaval.glo2003.beds.domain.Bed;
import java.util.List;

public class BedQueryList implements BedQuery {

  private final List<BedQueryList> queries;

  public BedQueryList(List<BedQueryList> queries) {
    this.queries = queries;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    queries.forEach(bedQuery -> bedQuery.filter(beds));

    return beds;
  }
}
