package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidBookingKeyException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import java.time.LocalDate;
import java.util.UUID;

public class BookingMapper {

  public Booking fromRequest(BookingRequest bookingRequest) {
    bookingRequestValidation(bookingRequest);
    LocalDate arrivalDate = BookingDateMapper.fromString(bookingRequest.getArrivalDate());
    return new Booking(
        UUID.randomUUID(),
        bookingRequest.getTenantPublicKey(),
        arrivalDate,
        bookingRequest.getNumberOfNights(),
        bookingRequest.getBookingPackage());
  }

  public BookingResponse toResponse(Booking booking) {
    return new BookingResponse(
        booking.getArrivalDate().toString(),
        booking.getNumberOfNights(),
        booking.getPackage(),
        booking.getTotal());
  }

  private void bookingRequestValidation(BookingRequest bookingRequest) {
    if (bookingRequest.getTenantPublicKey() == null) {
      throw new InvalidBookingKeyException();
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
