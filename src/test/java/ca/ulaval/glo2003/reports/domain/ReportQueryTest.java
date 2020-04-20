package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.time.domain.TimeDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReportQueryTest {

  private static ReportQuery query;

  private static ReportScope scope = mock(ReportScope.class);
  private static ReportPeriod firstPeriod = mock(ReportPeriod.class);
  private static ReportPeriod secondPeriod = mock(ReportPeriod.class);
  private static List<ReportMetric> metrics;
  private static ReportMetric firstMetric = mock(ReportMetric.class);
  private static ReportMetric secondMetric = mock(ReportMetric.class);
  private static List<ReportDimension> dimensions;
  private static ReportDimension firstDimension = mock(ReportDimension.class);
  private static ReportDimension secondDimension = mock(ReportDimension.class);
  private static List<ReportEvent> events;
  private static TimeDate date = aTimeDate().build();
  private static TimeDate otherDate = aTimeDate().build();
  private static ReportEvent firstEvent = aReportEvent().withDate(date).build();
  private static ReportEvent secondEvent = aReportEvent().withDate(otherDate).build();

  @BeforeAll
  private static void setUpPeriods() {
    when(firstPeriod.contains(date)).thenReturn(true);
    when(firstPeriod.contains(otherDate)).thenReturn(false);
    when(secondPeriod.contains(date)).thenReturn(false);
    when(secondPeriod.contains(otherDate)).thenReturn(true);
  }

  private void setUpQuery() {
    query = new ReportQuery(scope, metrics, dimensions);
    query.setEvents(events);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(scope);

    setUpScopeWithSinglePeriod();
    setUpScopeWithSingleDimension();
    setUpScopeWithSingleEvent();
  }

  private void setUpScopeWithSinglePeriod() {
    when(scope.getReportPeriods()).thenReturn(Collections.singletonList(firstPeriod));
    setUpQuery();
  }

  private void setUpScopeWithMultiplePeriods() {
    when(scope.getReportPeriods()).thenReturn(Arrays.asList(firstPeriod, secondPeriod));
    setUpQuery();
  }

  private void setUpScopeWithSingleDimension() {
    dimensions = Collections.singletonList(firstDimension);
    setUpQuery();
  }

  private void setUpScopeWithSingleEvent() {
    events = Collections.singletonList(firstEvent);
    setUpQuery();
  }

  private void setUpScopeWithMultipleEvents() {
    events = Arrays.asList(firstEvent, secondEvent);
    setUpQuery();
  }

  @Test
  public void execute_withSinglePeriod_shouldReturnSinglePeriod() {
    setUpScopeWithSinglePeriod();

    List<ReportPeriod> periods = query.execute();

    assertEquals(1, periods.size());
  }

  @Test
  public void execute_withMultiplePeriods_shouldReturnMultiplePeriods() {
    setUpScopeWithMultiplePeriods();

    List<ReportPeriod> periods = query.execute();

    assertEquals(2, periods.size());
  }

  @Test
  public void execute_shouldUseTransactionsWithinPeriods() {
    setUpScopeWithMultiplePeriods();
    setUpScopeWithMultipleEvents();

    query.execute();

    verify(firstPeriod).setSingleData(eq(Collections.singletonList(firstEvent)));
    verify(secondPeriod).setSingleData(eq(Collections.singletonList(secondEvent)));
  }

  // TODO : Testing isn't done yet.
}
