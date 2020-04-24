package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.converters.CancelationConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.CancelationRefundCalculator;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.reports.services.ReportService;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import javax.inject.Inject;

public class CancelationService {

  static final int MINIMUM_DAYS_FOR_CANCELATION = 1;
  static final int MINIMUM_DAYS_FOR_FULL_REFUND = 7;

  private final CancelationRefundCalculator cancelationRefundCalculator;
  private final CancelationConverter cancelationConverter;
  private final TransactionService transactionService;
  private final ReportService reportService;

  @Inject
  public CancelationService(
      CancelationRefundCalculator cancelationRefundCalculator,
      CancelationConverter cancelationConverter,
      TransactionService transactionService,
      ReportService reportService) {
    this.cancelationRefundCalculator = cancelationRefundCalculator;
    this.cancelationConverter = cancelationConverter;
    this.transactionService = transactionService;
    this.reportService = reportService;
  }

  public CancelationResponse cancel(Bed bed, Booking booking) {
    if (booking.isCanceled()) throw new BookingAlreadyCanceledException();

    Price refund;
    TimeDate now = TimeDate.now();

    if (now.plusDays(MINIMUM_DAYS_FOR_CANCELATION).isAfter(booking.getArrivalDate())) {
      throw new CancelationNotAllowedException();
    } else {
      refund =
          now.plusDays(MINIMUM_DAYS_FOR_FULL_REFUND).isAfter(booking.getArrivalDate())
              ? refundHalfTotal(bed, booking)
              : refundFullTotal(bed, booking);
    }

    booking.cancel();
    reportService.addCancelation(bed, booking);

    return cancelationConverter.toResponse(refund);
  }

  private Price refundHalfTotal(Bed bed, Booking booking) {
    Price tenantRefund = cancelationRefundCalculator.calculateTenantRefund(booking.getPrice());
    Price ownerRefund = cancelationRefundCalculator.calculateOwnerRefund(booking.getPrice());
    transactionService.addStayCanceledHalfRefund(
        booking.getTenantPublicKey().toString(),
        bed.getOwnerPublicKey().toString(),
        tenantRefund,
        ownerRefund,
        booking.getPrice(),
        booking.getDepartureDate().toTimestamp());
    return tenantRefund;
  }

  private Price refundFullTotal(Bed bed, Booking booking) {
    transactionService.addStayCanceledFullRefund(
        booking.getTenantPublicKey().toString(),
        bed.getOwnerPublicKey().toString(),
        booking.getPrice(),
        booking.getDepartureDate().toTimestamp());
    return booking.getPrice();
  }
}
