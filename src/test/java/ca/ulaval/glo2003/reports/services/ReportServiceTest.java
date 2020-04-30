package ca.ulaval.glo2003.reports.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo2003.reports.converters.ReportConverter;
import ca.ulaval.glo2003.reports.domain.ReportEventFactory;
import ca.ulaval.glo2003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo2003.reports.domain.ReportRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportServiceTest {
  private static ReportService reportService;
  private static ReportQueryFactory reportQueryFactory = mock(ReportQueryFactory.class);
  private static ReportEventFactory reportEventFactory = mock(ReportEventFactory.class);
  private static ReportConverter reportConverter = mock(ReportConverter.class);
  private static ReportRepository reportRepository = mock(ReportRepository.class);

  @BeforeAll
  public static void setUpService() {
    reset(reportRepository);
    reportService =
        new ReportService(
            reportQueryFactory, reportEventFactory, reportConverter, reportRepository);
  }

  @Test
  public void deleteAll_shouldDeleteAllReports() {
    reportService.deleteAll();

    verify(reportRepository).deleteAll();
  }
}
