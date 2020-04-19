package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import java.util.List;
import java.util.Map;

public class ScopeQueryParamAssembler implements ReportQueryParamAssembler {

  public static final String SCOPE_PARAM = "scope";

  public ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params) {
    List<String> scopes = params.get(SCOPE_PARAM);

    return scopes != null ? builder.withScope(ReportScopes.get(scopes.get(0))) : builder;
  }
}
