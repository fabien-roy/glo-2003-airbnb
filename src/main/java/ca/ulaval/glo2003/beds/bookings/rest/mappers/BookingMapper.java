package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidPublicKeyException;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.time.LocalDate;

public class BookingMapper {

  public static final String OWNER_PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";
  public static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

  public Booking fromRequest(BookingRequest bookingRequest) {
    validateBookingRequest(bookingRequest);
    validateDate(bookingRequest.getArrivalDate());
    validateNumberOfNights(bookingRequest.getNumberOfNights());
    validatePublicKey(bookingRequest.getTenantPublicKey());

    Packages bookingPackage = Packages.get(bookingRequest.getBookingPackage());

    return new Booking(
        bookingRequest.getTenantPublicKey(),
        LocalDate.parse(bookingRequest.getArrivalDate()),
        bookingRequest.getNumberOfNights(),
        bookingPackage);
  }

  public BookingResponse toResponse(Booking booking) {
    return new BookingResponse(
        booking.getArrivalDate().toString(), booking.getNumberOfNights(), booking.getPackage());
  }

  private void validateBookingRequest(BookingRequest request) {
    if (request.getArrivalDate() == null
        || request.getTenantPublicKey() == null
        || request.getBookingPackage() == null) {
      throw new InvalidFormatException();
    }
  }

  private void validateDate(String dateToMatch) {
    if (!dateToMatch.matches(DATE_PATTERN)) throw new InvalidArrivalDateException();
    if (LocalDate.parse(dateToMatch).isBefore(LocalDate.now()))
      throw new ArrivalDateInThePastException();
  }

  private void validateNumberOfNights(int numberOfNights) {
    if (numberOfNights < 1 || numberOfNights > 90) {
      throw new InvalidNumberOfNights();
    }
  }

  private void validatePublicKey(String publicKey) {
    if (!publicKey.matches(OWNER_PUBLIC_KEY_PATTERN)) throw new InvalidPublicKeyException();
  }
}
