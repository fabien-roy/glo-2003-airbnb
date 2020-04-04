package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.bookings.converters.CancelationConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.CancelationRefundCalculator;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import javax.inject.Inject;

public class CancelationService {

  static final int MINIMUM_DAYS_FOR_CANCELATION = 1;
  static final int MINIMUM_DAYS_FOR_FULL_REFUND = 7;

  private final CancelationRefundCalculator cancelationRefundCalculator;
  private final CancelationConverter cancelationConverter;
  private final TransactionService transactionService;

  @Inject
  public CancelationService(
      CancelationRefundCalculator cancelationRefundCalculator,
      CancelationConverter cancelationConverter,
      TransactionService transactionService) {
    this.cancelationRefundCalculator = cancelationRefundCalculator;
    this.cancelationConverter = cancelationConverter;
    this.transactionService = transactionService;
  }

  public CancelationResponse cancel(Booking booking, String bedOwner) {
    if (booking.isCanceled()) throw new BookingAlreadyCanceledException();

    Price refund;
    BookingDate now = BookingDate.now();

    if (now.plusDays(MINIMUM_DAYS_FOR_CANCELATION).isAfter(booking.getArrivalDate())) {
      throw new CancelationNotAllowedException();
    } else {
      refund =
          now.plusDays(MINIMUM_DAYS_FOR_FULL_REFUND).isAfter(booking.getArrivalDate())
              ? refundHalfTotal(booking, bedOwner)
              : refundFullTotal(booking, bedOwner);
    }

    booking.cancel();

    return cancelationConverter.toResponse(refund);
  }

  private Price refundHalfTotal(Booking booking, String bedOwner) {
    Price tenantRefund = cancelationRefundCalculator.calculateTenantRefund(booking.getTotal());
    Price ownerRefund = cancelationRefundCalculator.calculateOwnerRefund(booking.getTotal());
    transactionService.addStayCanceledHalfRefund(
        booking.getTenantPublicKey().getValue(),
        bedOwner,
        tenantRefund,
        ownerRefund,
        booking.getTotal(),
        booking.getNumberOfNights());
    return tenantRefund;
  }

  private Price refundFullTotal(Booking booking, String bedOwner) {
    transactionService.addStayCanceledFullRefund(
        booking.getTenantPublicKey().getValue(),
        bedOwner,
        booking.getTotal(),
        booking.getNumberOfNights());
    return booking.getTotal();
  }
}
