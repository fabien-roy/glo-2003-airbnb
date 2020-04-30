package ca.ulaval.glo2003.reports.infrastructure;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodBuilder.aReportPeriod;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportRepository;
import ca.ulaval.glo2003.time.domain.TimeDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryReportRepositoryTest {

  private ReportRepository reportRepository;

  private List<ReportPeriod> filteredPeriods;
  private ReportEvent event;
  private InMemoryReportQuery reportQuery = mock(InMemoryReportQuery.class);

  @BeforeEach
  public void setUpRepository() {
    reportRepository = new InMemoryReportRepository();
    event = aReportEvent().build();
  }

  @BeforeEach
  public void setUpQuery() {
    reset(reportQuery);
    filteredPeriods = Collections.singletonList(aReportPeriod().build());
    when(reportQuery.execute()).thenReturn(filteredPeriods);
  }

  @Test
  public void getAll_withQuery_shouldReturnFilteredPeriods() {
    reportRepository.addEvent(event);

    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);

    assertSame(filteredPeriods, periods);
  }

  @Test
  public void getAll_withQuery_shouldUseQuery() {
    reportRepository.addEvent(event);

    reportRepository.getPeriods(reportQuery);

    verify(reportQuery).setEvents(eq(Collections.singletonList(event)));
  }

  @Test
  public void deleteAll_shouldClearAllReports() {
    ReportEvent reportEvent = aReportEvent().withDate(TimeDate.now()).build();
    reportRepository.addEvent(reportEvent);

    reportRepository.deleteAll();
    reportRepository.getPeriods(reportQuery);

    verify(reportQuery).setEvents(eq(Collections.emptyList()));
  }
}
