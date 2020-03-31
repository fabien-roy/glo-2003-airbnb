package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class BedQueryFactory {

  private final BedQueryBuilder bedQueryBuilder;
  private final Set<BedQueryParamAssembler> queryParamAssemblers;

  @Inject
  public BedQueryFactory(
      BedQueryBuilder bedQueryBuilder, Set<BedQueryParamAssembler> queryParamAssemblers) {
    this.bedQueryBuilder = bedQueryBuilder;
    this.queryParamAssemblers = queryParamAssemblers;
  }

  public InMemoryBedQuery create(Map<String, List<String>> params) {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery();

    for (BedQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, params);

    return builder.build();
  }
}
