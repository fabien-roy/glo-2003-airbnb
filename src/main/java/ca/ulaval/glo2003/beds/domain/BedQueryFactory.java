package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;
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

  public BedQuery create(Map<String, String[]> params) {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery();

    for (BedQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, params);

    return builder.build();
  }
}
