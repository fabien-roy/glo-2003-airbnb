package ca.ulaval.glo2003.reports.services;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodBuilder.aReportPeriod;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.reports.converters.ReportPeriodConverter;
import ca.ulaval.glo2003.reports.domain.*;
import ca.ulaval.glo2003.reports.rest.ReportPeriodResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportServiceTest {

  private static ReportService reportService;
  private static ReportQueryFactory reportQueryFactory = mock(ReportQueryFactory.class);
  private static ReportEventFactory reportEventFactory = mock(ReportEventFactory.class);
  private static ReportPeriodConverter reportPeriodConverter = mock(ReportPeriodConverter.class);
  private static ReportRepository reportRepository = mock(ReportRepository.class);

  private Bed bed = mock(Bed.class);
  private Booking booking = mock(Booking.class);
  private ReportEvent reservationEvent = mock(ReportEvent.class);
  private ReportEvent cancelationEvent = mock(ReportEvent.class);
  private ReportPeriod period = aReportPeriod().build();
  private ReportPeriod otherPeriod = aReportPeriod().build();
  private ReportPeriodResponse periodResponse = mock(ReportPeriodResponse.class);
  private ReportPeriodResponse otherPeriodResponse = mock(ReportPeriodResponse.class);
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpService() {
    reset(reportRepository);
    reportService =
        new ReportService(
            reportQueryFactory, reportEventFactory, reportPeriodConverter, reportRepository);
  }

  @BeforeEach
  public void resetMocks() {
    reset(reportRepository);
  }

  private void setUpMocksForGetAll() {
    ReportQuery bedQuery = mock(ReportQuery.class);
    when(reportQueryFactory.create(params)).thenReturn(bedQuery);
    List<ReportPeriod> periods = Arrays.asList(period, otherPeriod);
    when(reportRepository.getPeriods(bedQuery)).thenReturn(periods);
    List<ReportPeriodResponse> periodResponses = Arrays.asList(periodResponse, otherPeriodResponse);
    when(reportPeriodConverter.toResponses(periods)).thenReturn(periodResponses);
  }

  private void setUpMocksForAddReservation() {
    when(reportEventFactory.create(ReportEventTypes.RESERVATION, bed, booking))
        .thenReturn(reservationEvent);
  }

  private void setUpMocksForAddCancelation() {
    when(reportEventFactory.create(ReportEventTypes.CANCELATION, bed, booking))
        .thenReturn(cancelationEvent);
  }

  @Test
  public void getAll_shouldGetReportsWithQuery() {
    setUpMocksForGetAll();

    List<ReportPeriodResponse> periodResponses = reportService.getAll(params);

    assertTrue(periodResponses.contains(periodResponse));
    assertTrue(periodResponses.contains(otherPeriodResponse));
  }

  @Test
  public void addReservation_shouldAddReservationEvent() {
    setUpMocksForAddReservation();

    reportService.addReservation(bed, booking);

    verify(reportRepository).addEvent(reservationEvent);
  }

  @Test
  public void addCancelation_shouldAddCancelationEvent() {
    setUpMocksForAddCancelation();

    reportService.addCancelation(bed, booking);

    verify(reportRepository).addEvent(cancelationEvent);
  }

  @Test
  public void deleteAll_shouldDeleteAllReports() {
    reportService.deleteAll();

    verify(reportRepository).deleteAll();
  }
}
