package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidBookingKeyException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.rest.mappers.DateMapper;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class BookingMapper {

  public Booking fromRequest(BookingRequest bookingRequest) {
    bookingRequestValidation(bookingRequest);
    Date arrivalDate = null;
    try {
      arrivalDate = DateMapper.fromString(bookingRequest.getArrivalDate());
    } catch (ParseException e) {
      e.printStackTrace();
    }
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
    if (DateMapper.isNotAValidDate(bookingRequest.getArrivalDate())) {
      throw new InvalidArrivalDateException();
    }
    if (DateMapper.isBeforeToday(bookingRequest.getArrivalDate())) {
      throw new ArrivalDateInThePastException();
    }
    if (bookingRequest.getNumberOfNights() < 1) {
      throw new InvalidNumberOfNights();
    }
  }
}
