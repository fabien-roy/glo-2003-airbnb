package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportScopeException;
import java.util.HashMap;
import java.util.Map;

public enum ReportScopes {
  WEEKLY("weekly"),
  MONTHLY("monthly"),
  QUARTERLY("quarterly"),
  ANNUAL("annual");

  private String scope;
  private static final Map<String, ReportScopes> lookup = new HashMap<>();

  static {
    for (ReportScopes scope : ReportScopes.values()) {
      lookup.put(scope.toString(), scope);
    }
  }

  ReportScopes(String scope) {
    this.scope = scope;
  }

  @Override
  public String toString() {
    return scope;
  }

  public static ReportScopes get(String scope) {
    ReportScopes foundScope = lookup.get(scope);

    if (foundScope == null) throw new InvalidReportScopeException();

    return foundScope;
  }
}
