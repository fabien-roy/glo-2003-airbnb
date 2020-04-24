package ca.ulaval.glo2003.reports.domain.scopes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnualScopeTest {

  private static AnnualScope reportScope;
  private static TimePeriod period = mock(TimePeriod.class);
  private static TimeYear firstYear = mock(TimeYear.class);
  private static TimeYear secondYear = mock(TimeYear.class);

  @BeforeEach
  public void resetMocks() {
    reset(period);
  }

  private void setUpReportScopeWithSingleYear() {
    when(period.getYears()).thenReturn(Collections.singletonList(firstYear));
    setUpReportScope();
  }

  private void setUpReportScopeWithMultipleYears() {
    when(period.getYears()).thenReturn(Arrays.asList(firstYear, secondYear));
    setUpReportScope();
  }

  private void setUpReportScope() {
    reportScope = new AnnualScope(period);
  }

  @Test
  public void getReportPeriods_withSingleYear_shouldHaveSingleReport() {
    setUpReportScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(1, reportPeriods.size());
  }

  @Test
  public void getReportPeriods_withSingleYear_shouldSetPeriodNamesToThatYear() {
    setUpReportScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYear.toString(), reportPeriods.get(0).getName());
  }

  @Test
  public void getReportPeriods_withSingleYear_shouldSetPeriodToThatYear() {
    setUpReportScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYear.toPeriod(), reportPeriods.get(0).getPeriod());
  }

  @Test
  public void getReportPeriods_withMultipleYears_shouldHaveMultipleReports() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(2, reportPeriods.size());
  }

  @Test
  public void getReportPeriods_withMultipleYears_shouldSetPeriodNamesToThoseYears() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYear.toString(), reportPeriods.get(0).getName());
    assertEquals(secondYear.toString(), reportPeriods.get(1).getName());
  }

  @Test
  public void getReportPeriods_withMultipleYears_shouldSetPeriodToThoseYears() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYear.toPeriod(), reportPeriods.get(0).getPeriod());
    assertEquals(secondYear.toPeriod(), reportPeriods.get(1).getPeriod());
  }
}
