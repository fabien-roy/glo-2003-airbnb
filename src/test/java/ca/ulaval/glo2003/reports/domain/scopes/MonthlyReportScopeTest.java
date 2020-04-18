package ca.ulaval.glo2003.reports.domain.scopes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonthlyReportScopeTest {

  private static MonthlyReportScope reportScope;
  private static TimePeriod period = mock(TimePeriod.class);
  private static TimeMonth firstMonth = mock(TimeMonth.class);
  private static String firstMonthName = "firstMonth";
  private static TimePeriod firstMonthPeriod = mock(TimePeriod.class);
  private static TimeMonth secondMonth = mock(TimeMonth.class);
  private static String secondMonthName = "secondMonth";
  private static TimePeriod secondMonthPeriod = mock(TimePeriod.class);

  @BeforeAll
  public static void setUpMocks() {
    when(firstMonth.toString()).thenReturn(firstMonthName);
    when(firstMonth.toPeriod()).thenReturn(firstMonthPeriod);
    when(secondMonth.toString()).thenReturn(secondMonthName);
    when(secondMonth.toPeriod()).thenReturn(secondMonthPeriod);
  }

  @BeforeEach
  public void resetMocks() {
    reset(period);
  }

  private void setUpReportScopeWithSingleMonth() {
    when(period.getMonths()).thenReturn(Collections.singletonList(firstMonth));
    setUpReportScope();
  }

  private void setUpReportScopeWithMultipleMonths() {
    when(period.getMonths()).thenReturn(Arrays.asList(firstMonth, secondMonth));
    setUpReportScope();
  }

  private void setUpReportScope() {
    reportScope = new MonthlyReportScope(period);
  }

  @Test
  public void getReportPeriod_withSingleMonth_shouldHaveSingleReport() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(1, reportPeriods.size());
  }

  @Test
  public void getReportPeriod_withSingleMonth_shouldSetPeriodNamesToThatMonth() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toString(), reportPeriods.get(0).getName());
  }

  @Test
  public void getReportPeriod_withSingleMonth_shouldSetPeriodToThatMonth() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toPeriod(), reportPeriods.get(0).getPeriod());
  }

  @Test
  public void getReportPeriod_withMultipleMonthsInSameYear_shouldHaveSingleReport() {
    setUpReportScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(2, reportPeriods.size());
  }

  @Test
  public void getReportPeriod_withMultipleMonthsInSameYear_shouldSetPeriodNamesToThoseMonths() {
    setUpReportScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toString(), reportPeriods.get(0).getName());
    assertEquals(secondMonth.toString(), reportPeriods.get(1).getName());
  }
}
