package ca.ulaval.glo2003.reports.infrastructure;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.time.domain.TimeDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryReportQueryTest {

  private static InMemoryReportQuery query;

  private static ReportScope scope = mock(ReportScope.class);
  private static ReportPeriod firstPeriod = mock(ReportPeriod.class);
  private static ReportPeriod secondPeriod = mock(ReportPeriod.class);
  private static List<ReportMetric> metrics = new ArrayList<>();
  private static ReportMetric firstMetric = mock(ReportMetric.class);
  private static ReportMetric secondMetric = mock(ReportMetric.class);
  private static List<ReportDimension> dimensions = new ArrayList<>();
  private static ReportDimension firstDimension = mock(ReportDimension.class);
  private static ReportDimension secondDimension = mock(ReportDimension.class);
  private static List<ReportEvent> events = new ArrayList<>();
  private static TimeDate date = aTimeDate().build();
  private static TimeDate otherDate = aTimeDate().build();
  private static ReportEvent firstEvent = aReportEvent().withDate(date).build();
  private static ReportEvent secondEvent = aReportEvent().withDate(otherDate).build();
  private static ReportPeriodData firstData = aReportPeriodData().build();
  private static ReportPeriodData secondData = aReportPeriodData().build();
  private static ReportPeriodData dimensionedData = aReportPeriodData().build();
  private static int numberOfFirstDimensions = 2;
  private static int numberOfSecondDimensions = 6;

  private void setUpQuery() {
    query = new InMemoryReportQuery(scope, metrics, dimensions);
    query.setEvents(events);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(scope, firstPeriod, secondPeriod, firstMetric, secondMetric);

    setUpPeriods();
    setUpDimensions();

    setUpScopeWithSinglePeriod();
    setUpScopeWithSingleMetric();
    setUpScopeWithNoDimension();
    setUpScopeWithSingleEvent();
  }

  private void setUpPeriods() {
    when(firstPeriod.contains(date)).thenReturn(true);
    when(firstPeriod.contains(otherDate)).thenReturn(false);
    when(firstPeriod.getData()).thenReturn(Collections.singletonList(firstData));
    when(secondPeriod.contains(date)).thenReturn(false);
    when(secondPeriod.contains(otherDate)).thenReturn(true);
    when(secondPeriod.getData()).thenReturn(Collections.singletonList(secondData));
  }

  private void setUpDimensions() {
    when(firstDimension.splitAll(anyList()))
        .thenReturn(Collections.nCopies(numberOfFirstDimensions, dimensionedData));
    when(secondDimension.splitAll(anyList()))
        .thenReturn(Collections.nCopies(numberOfSecondDimensions, dimensionedData));
  }

  private void setUpScopeWithSinglePeriod() {
    when(scope.getReportPeriods()).thenReturn(Collections.singletonList(firstPeriod));
    setUpQuery();
  }

  private void setUpScopeWithMultiplePeriods() {
    when(scope.getReportPeriods()).thenReturn(Arrays.asList(firstPeriod, secondPeriod));
    setUpQuery();
  }

  private void setUpScopeWithSingleMetric() {
    metrics = Collections.singletonList(firstMetric);
    setUpQuery();
  }

  private void setUpScopeWithMultipleMetrics() {
    metrics = Arrays.asList(firstMetric, secondMetric);
    setUpQuery();
  }

  private void setUpScopeWithNoDimension() {
    dimensions = Collections.emptyList();
    setUpQuery();
  }

  private void setUpScopeWithSingleDimension() {
    dimensions = Collections.singletonList(firstDimension);
    setUpQuery();
  }

  private void setUpScopeWithMultipleDimensions() {
    dimensions = Arrays.asList(firstDimension, secondDimension);
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

  @Test
  public void execute_withSingleDimension_shouldSplitDataAccordingToDimension() {
    setUpScopeWithSingleDimension();

    query.execute();

    verify(firstPeriod).setData(argThat(data -> data.size() == numberOfFirstDimensions));
  }

  @Test
  public void execute_withMultipleDimensions_shouldSplitDataAccordingToDimension() {
    setUpScopeWithMultipleDimensions();

    query.execute();

    verify(firstPeriod).setData(argThat(data -> data.size() == numberOfSecondDimensions));
  }

  @Test
  public void execute_withSingleMetric_shouldCalculateMetricForEachDimensionedData() {
    setUpScopeWithMultipleDimensions();

    query.execute();

    verify(firstMetric, times(numberOfSecondDimensions)).calculate(any());
    verify(secondMetric, never()).calculate(any());
  }

  @Test
  public void execute_withMultipleMetrics_shouldCalculateMetricsForEachDimensionedData() {
    setUpScopeWithMultipleDimensions();
    setUpScopeWithMultipleMetrics();

    query.execute();

    verify(firstMetric, times(numberOfSecondDimensions)).calculate(any());
    verify(secondMetric, times(numberOfSecondDimensions)).calculate(any());
  }
}
