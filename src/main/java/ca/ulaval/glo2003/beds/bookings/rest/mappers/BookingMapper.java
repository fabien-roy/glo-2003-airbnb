package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.rest.mappers.PriceMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PublicKeyMapper;
import java.time.LocalDate;

public class BookingMapper {

  public static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
  private PublicKeyMapper publicKeyMapper;
  private PriceMapper priceMapper;

  public BookingMapper(PublicKeyMapper publicKeyMapper, PriceMapper priceMapper) {
    this.publicKeyMapper = publicKeyMapper;
    this.priceMapper = priceMapper;
  }

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateRequest(bookingRequest);

    PublicKey tenantPublicKey = publicKeyMapper.fromString(bookingRequest.getTenantPublicKey());
    Packages bookingPackage = Packages.get(bookingRequest.getBookingPackage());

    return new Booking(
        tenantPublicKey,
        LocalDate.parse(bookingRequest.getArrivalDate()),
        bookingRequest.getNumberOfNights(),
        bookingPackage);
  }

  public BookingResponse toResponse(Booking booking) {
    Double total = priceMapper.toDouble(booking.getTotal());

    return new BookingResponse(
        booking.getArrivalDate().toString(),
        booking.getNumberOfNights(),
        booking.getPackage(),
        total);
  }

  private void validateRequest(BookingRequest request) {
    validateArrivalDate(request.getArrivalDate());
    validateNumberOfNights(request.getNumberOfNights());
  }

  private void validateArrivalDate(String arrivalDate) {
    if (arrivalDate == null || !arrivalDate.matches(DATE_PATTERN))
      throw new InvalidArrivalDateException();

    if (LocalDate.parse(arrivalDate).isBefore(LocalDate.now()))
      throw new ArrivalDateInThePastException();
  }

  private void validateNumberOfNights(int numberOfNights) {
    if (numberOfNights < 1 || numberOfNights > 90) {
      throw new InvalidNumberOfNights();
    }
  }
}
