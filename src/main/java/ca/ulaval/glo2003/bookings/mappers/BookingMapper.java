package ca.ulaval.glo2003.bookings.mappers;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.mappers.PriceMapper;
import ca.ulaval.glo2003.beds.mappers.PublicKeyConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import javax.inject.Inject;

public class BookingMapper {

  private final PublicKeyConverter publicKeyConverter;
  private final BookingDateMapper bookingDateMapper;
  private final PriceMapper priceMapper;

  @Inject
  public BookingMapper(
      PublicKeyConverter publicKeyConverter,
      BookingDateMapper bookingDateMapper,
      PriceMapper priceMapper) {
    this.publicKeyConverter = publicKeyConverter;
    this.bookingDateMapper = bookingDateMapper;
    this.priceMapper = priceMapper;
  }

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateNumberOfNights(bookingRequest.getNumberOfNights());
    validateColonySize(bookingRequest.getColonySize());

    PublicKey tenantPublicKey = publicKeyConverter.fromString(bookingRequest.getTenantPublicKey());
    BookingDate arrivalDate = bookingDateMapper.fromString(bookingRequest.getArrivalDate());
    Packages bookingPackage = Packages.get(bookingRequest.getBookingPackage());

    return new Booking(
        tenantPublicKey,
        arrivalDate,
        bookingRequest.getNumberOfNights(),
        bookingRequest.getColonySize(),
        bookingPackage);
  }

  public BookingResponse toResponse(Booking booking) {
    String arrivalDate = bookingDateMapper.toString(booking.getArrivalDate());
    Double total = priceMapper.toDouble(booking.getTotal());

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

  private void validateColonySize(int colonySize) {
    if (colonySize < 0) {
      throw new InvalidColonySizeException();
    }
  }
}
