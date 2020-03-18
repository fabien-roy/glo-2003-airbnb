package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
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
  private final PublicKeyMapper publicKeyMapper;
  private final BookingDateMapper bookingDateMapper;
  private final PriceMapper priceMapper;

  public BookingMapper(
      PublicKeyMapper publicKeyMapper,
      BookingDateMapper bookingDateMapper,
      PriceMapper priceMapper) {
    this.publicKeyMapper = publicKeyMapper;
    this.bookingDateMapper = bookingDateMapper;
    this.priceMapper = priceMapper;
  }

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateRequest(bookingRequest);

    PublicKey tenantPublicKey = publicKeyMapper.fromString(bookingRequest.getTenantPublicKey());
    BookingDate arrivalDate = bookingDateMapper.fromString(bookingRequest.getArrivalDate());
    Packages bookingPackage = Packages.get(bookingRequest.getBookingPackage());

    return new Booking(
        tenantPublicKey, arrivalDate, bookingRequest.getNumberOfNights(), bookingPackage);
  }

  public BookingResponse toResponse(Booking booking) {
    String arrivalDate = bookingDateMapper.toString(booking.getArrivalDate());
    Double total = priceMapper.toDouble(booking.getTotal());

    return new BookingResponse(
        arrivalDate, booking.getNumberOfNights(), booking.getPackage(), total);
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
