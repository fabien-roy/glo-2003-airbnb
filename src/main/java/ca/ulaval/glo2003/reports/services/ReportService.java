package ca.ulaval.glo2003.reports.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.reports.converters.ReportConverter;
import ca.ulaval.glo2003.reports.domain.*;
import ca.ulaval.glo2003.reports.rest.ReportPeriodResponse;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class ReportService {

  private final ReportQueryFactory reportQueryFactory;
  private final ReportEventFactory reportEventFactory;
  private final ReportConverter reportConverter;
  private final ReportRepository reportRepository;

  @Inject
  public ReportService(
      ReportQueryFactory reportQueryFactory,
      ReportEventFactory reportEventFactory,
      ReportConverter reportConverter,
      ReportRepository reportRepository) {
    this.reportQueryFactory = reportQueryFactory;
    this.reportEventFactory = reportEventFactory;
    this.reportConverter = reportConverter;
    this.reportRepository = reportRepository;
  }

  // TODO : Logic and tests of ReportService.getAll(...) (#331)
  public List<ReportPeriodResponse> getAll(Map<String, List<String>> params) {
    ReportQuery reportQuery = reportQueryFactory.create(params);
    List<ReportPeriod> periods = reportRepository.getPeriods(reportQuery);
    return reportConverter.toResponses(periods);
  }

  // TODO : Test ReportService.addReservation
  public void addReservation(Bed bed, Booking booking) {
    ReportEvent event = reportEventFactory.create(ReportEventTypes.RESERVATION, bed, booking);
    reportRepository.add(event);
  }

  // TODO : Test ReportService.addCancelation
  public void addCancelation(Bed bed, Booking booking) {
    ReportEvent event = reportEventFactory.create(ReportEventTypes.CANCELATION, bed, booking);
    reportRepository.add(event);
  }
}
