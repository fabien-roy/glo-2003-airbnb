package ca.ulaval.glo2003.reports.domain.scopes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnualReportScopeTest {

  private static AnnualReportScope reportScope;
  private static ReservationPeriod period = mock(ReservationPeriod.class);
  private static int firstYear = 2020;
  private static int secondYear = 2021;
  private static ReservationPeriod firstYearPeriod = ReservationPeriod.ofYear(firstYear);
  private static ReservationPeriod secondYearPeriod = ReservationPeriod.ofYear(secondYear);

  @BeforeEach
  public void resetMocks() {
    reset(period);
  }

  private void setUpReportScopeWithOneYear() {
    when(period.getYears()).thenReturn(Collections.singletonList(firstYear));
    setUpReportScope();
  }

  private void setUpReportScopeWithMultipleYears() {
    when(period.getYears()).thenReturn(Arrays.asList(firstYear, secondYear));
    setUpReportScope();
  }

  private void setUpReportScope() {
    reportScope = new AnnualReportScope(period);
  }

  @Test
  public void getReportPeriod_withOneYear_shouldHaveSingleReport() {
    setUpReportScopeWithOneYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(1, reportPeriods.size());
  }

  @Test
  public void getReportPeriod_withOneYear_shouldSetPeriodNamesToThatYear() {
    setUpReportScopeWithOneYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(String.valueOf(firstYear), reportPeriods.get(0).getName());
  }

  @Test
  public void getReportPeriod_withSingleYear_shouldSetPeriodToThatYear() {
    setUpReportScopeWithOneYear();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYearPeriod, reportPeriods.get(0).getPeriod());
  }

  @Test
  public void getReportPeriod_withMultipleYears_shouldHaveMultipleReports() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(2, reportPeriods.size());
  }

  @Test
  public void getReportPeriod_withMultipleYears_shouldSetPeriodNamesToThoseYears() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(String.valueOf(firstYear), reportPeriods.get(0).getName());
    assertEquals(String.valueOf(secondYear), reportPeriods.get(1).getName());
  }

  @Test
  public void getReportPeriod_withMultipleYears_shouldSetPeriodToThoseYears() {
    setUpReportScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = reportScope.getReportPeriods();

    assertEquals(firstYearPeriod, reportPeriods.get(0).getPeriod());
    assertEquals(secondYearPeriod, reportPeriods.get(1).getPeriod());
  }
}
