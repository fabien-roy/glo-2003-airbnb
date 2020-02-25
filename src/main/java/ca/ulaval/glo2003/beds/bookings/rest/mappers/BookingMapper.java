package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidPublicKeyException;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import java.time.LocalDate;

public class BookingMapper {

  public Booking fromRequest(BookingRequest bookingRequest) {
    bookingRequestValidation(bookingRequest);
    LocalDate arrivalDate = BookingDateMapper.fromString(bookingRequest.getArrivalDate());
    return new Booking(
        bookingRequest.getTenantPublicKey(),
        arrivalDate,
        bookingRequest.getNumberOfNights(),
        PackageNames.get(bookingRequest.getBookingPackage()));
  }

  public BookingResponse toResponse(Booking booking) {
    return new BookingResponse(
        booking.getArrivalDate().toString(), booking.getNumberOfNights(), booking.getPackageName());
  }

  private void bookingRequestValidation(BookingRequest bookingRequest) {
    if (bookingRequest.getTenantPublicKey() == null) {
      throw new InvalidPublicKeyException();
    }
    if (BookingDateMapper.isNotAValidDate(bookingRequest.getArrivalDate())) {
      throw new InvalidArrivalDateException();
    }
    if (BookingDateMapper.isBeforeToday(bookingRequest.getArrivalDate())) {
      throw new ArrivalDateInThePastException();
    }
    if (bookingRequest.getNumberOfNights() < 1) {
      throw new InvalidNumberOfNights();
    }
  }
}
