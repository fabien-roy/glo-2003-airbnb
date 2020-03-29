package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.beds.converters.PublicKeyConverter;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import javax.inject.Inject;

public class BookingConverter {

  private final PublicKeyConverter publicKeyConverter;
  private final BookingDateConverter bookingDateConverter;
  private final PriceConverter priceConverter;

  @Inject
  public BookingConverter(
      PublicKeyConverter publicKeyConverter,
      BookingDateConverter bookingDateConverter,
      PriceConverter priceConverter) {
    this.publicKeyConverter = publicKeyConverter;
    this.bookingDateConverter = bookingDateConverter;
    this.priceConverter = priceConverter;
  }

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateNumberOfNights(bookingRequest.getNumberOfNights());

    PublicKey tenantPublicKey = publicKeyConverter.fromString(bookingRequest.getTenantPublicKey());
    BookingDate arrivalDate = bookingDateConverter.fromString(bookingRequest.getArrivalDate());

    return new Booking(
        tenantPublicKey,
        arrivalDate,
        bookingRequest.getNumberOfNights(),
        bookingRequest.getColonySize(),
        Packages.get(bookingRequest.getBookingPackage()));
  }

  public BookingResponse toResponse(Booking booking) {
    String arrivalDate = bookingDateConverter.toString(booking.getArrivalDate());
    Double total = priceConverter.toDouble(booking.getTotal());

    return new BookingResponse(
        arrivalDate,
        booking.getNumberOfNights(),
        booking.getColonySize(),
        booking.getPackage().toString(),
        total,
        booking.getStatus().toString());
  }

  private void validateNumberOfNights(int numberOfNights) {
    if (numberOfNights < 1 || numberOfNights > 90) {
      throw new InvalidNumberOfNightsException();
    }
  }
}
