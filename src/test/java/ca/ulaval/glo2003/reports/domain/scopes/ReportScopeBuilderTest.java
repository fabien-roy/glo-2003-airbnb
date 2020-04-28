package ca.ulaval.glo2003.reports.domain.scopes;

import static ca.ulaval.glo2003.time.domain.helpers.TimePeriodBuilder.aTimePeriod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReportScopeBuilderTest {

  private static ReportScopeBuilder scopeBuilder;

  @BeforeAll
  public static void setUpBuilder() {
    scopeBuilder = new ReportScopeBuilder();
  }

  @Test
  public void build_withoutScope_shouldBuildMonthlyScope() {
    ReportScope scopes = scopeBuilder.aScope().build();

    assertTrue(scopes instanceof MonthlyScope);
  }

  @ParameterizedTest
  @MethodSource("provideScopes")
  public void build_withScope_shouldBuildScope(
      ReportScopes scopeType, Class<? extends ReportScope> scopeClass) {
    ReportScope scope = scopeBuilder.aScope().withType(scopeType).build();

    assertTrue(scopeClass.isInstance(scope));
  }

  private static Stream<Arguments> provideScopes() {
    return Stream.of(
        Arguments.of(ReportScopes.WEEKLY, WeeklyScope.class),
        Arguments.of(ReportScopes.MONTHLY, MonthlyScope.class),
        Arguments.of(ReportScopes.QUARTERLY, QuarterlyScope.class),
        Arguments.of(ReportScopes.ANNUAL, AnnualScope.class));
  }

  @Test
  public void build_withoutPeriod_shouldSetPeriodToNow() {
    LocalDate now = LocalDate.now();

    ReportScope scope = scopeBuilder.aScope().build();

    assertEquals(now, scope.getPeriod().getStart().toLocalDate());
    assertEquals(now, scope.getPeriod().getEnd().toLocalDate());
  }

  @Test
  public void build_withPeriod_shouldSetPeriod() {
    TimePeriod period = aTimePeriod().build();

    ReportScope scope = scopeBuilder.aScope().withPeriod(period).build();

    assertEquals(period, scope.getPeriod());
  }
}
