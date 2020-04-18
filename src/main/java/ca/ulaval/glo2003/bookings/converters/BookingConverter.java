package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.beds.converters.PublicKeyConverter;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.time.converters.TimeDateConverter;
import ca.ulaval.glo2003.time.domain.TimeDate;
import javax.inject.Inject;

public class BookingConverter {

  private final PublicKeyConverter publicKeyConverter;
  private final TimeDateConverter timeDateConverter;

  @Inject
  public BookingConverter(
      PublicKeyConverter publicKeyConverter, TimeDateConverter timeDateConverter) {
    this.publicKeyConverter = publicKeyConverter;
    this.timeDateConverter = timeDateConverter;
  }

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateNumberOfNights(bookingRequest.getNumberOfNights());
    validateColonySize(bookingRequest.getColonySize());

    PublicKey tenantPublicKey = publicKeyConverter.fromString(bookingRequest.getTenantPublicKey());
    TimeDate arrivalDate = timeDateConverter.fromString(bookingRequest.getArrivalDate());

    return new Booking(
        tenantPublicKey,
        arrivalDate,
        bookingRequest.getNumberOfNights(),
        bookingRequest.getColonySize(),
        Packages.get(bookingRequest.getBookingPackage()));
  }

  public BookingResponse toResponse(Booking booking) {
    return new BookingResponse(
        booking.getArrivalDate().toString(),
        booking.getNumberOfNights(),
        booking.getPackage().toString(),
        booking.getTotal().toDouble(),
        booking.getStatus().toString());
  }

  private void validateNumberOfNights(int numberOfNights) {
    if (numberOfNights < 1 || numberOfNights > 90) {
      throw new InvalidNumberOfNightsException();
    }
  }

  private void validateColonySize(Integer colonySize) {
    if (colonySize != null && colonySize < 1) {
      throw new InvalidColonySizeException();
    }
  }
}
