package ca.ulaval.glo2003.reports.domain.scopes;

import static ca.ulaval.glo2003.time.domain.helpers.TimeMonthBuilder.aTimeMonth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonthlyScopeTest {

  private static MonthlyScope reportScope;
  private static TimePeriod period = mock(TimePeriod.class);
  private static TimeMonth firstMonth = aTimeMonth().build();
  private static TimeMonth secondMonth = firstMonth.plusMonths(1);

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
    reportScope = new MonthlyScope(period);
  }

  @Test
  public void getReportPeriods_withSingleMonth_shouldHaveSingleReport() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(1, reportPeriods.size());
  }

  @Test
  public void getReportPeriods_withSingleMonth_shouldSetPeriodNamesToThatMonth() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toString(), reportPeriods.get(0).getName());
  }

  @Test
  public void getReportPeriods_withSingleMonth_shouldSetPeriodToThatMonth() {
    setUpReportScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toPeriod(), reportPeriods.get(0).getPeriod());
  }

  @Test
  public void getReportPeriods_withMultipleMonthsInSameYear_shouldHaveSingleReport() {
    setUpReportScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(2, reportPeriods.size());
  }

  @Test
  public void getReportPeriods_withMultipleMonthsInSameYear_shouldSetPeriodNamesToThoseMonths() {
    setUpReportScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toString(), reportPeriods.get(0).getName());
    assertEquals(secondMonth.toString(), reportPeriods.get(1).getName());
  }

  @Test
  public void getReportPeriods_withMultipleMonths_shouldSetPeriodToThoseMonths() {
    setUpReportScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstMonth.toPeriod(), reportPeriods.get(0).getPeriod());
    assertEquals(secondMonth.toPeriod(), reportPeriods.get(1).getPeriod());
  }
}
