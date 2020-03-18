package ca.ulaval.glo2003.beds.bookings.rest.factories;

import ca.ulaval.glo2003.beds.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorResponseFactory;

public class BookingErrorResponseFactory extends BedErrorResponseFactory {

  @Override
  public String create(Exception exception) {
    if (exception instanceof BookingNotFoundException) {
      return bookingNotFound(((BookingNotFoundException) exception).getBookingNumber());
    } else if (exception instanceof InvalidArrivalDateException) {
      return invalidArrivalDate();
    } else if (exception instanceof InvalidNumberOfNights) {
      return invalidNumberOfNights();
    } else if (exception instanceof ArrivalDateInThePastException) {
      return arrivalDateInThePast();
    } else {
      return defaultResponse();
    }
  }

  static String bookingNotFound(String number) {
    return tryWriteValueAsString(
        "BOOKING_NOT_FOUND", "booking with number " + number + " could not be found");
  }

  static String invalidArrivalDate() {
    return tryWriteValueAsString(
        "INVALID_ARRIVAL_DATE", "arrival date should be formatted as YYYY-MM-DD");
  }

  static String invalidNumberOfNights() {
    return tryWriteValueAsString(
        "INVALID_NUMBER_OF_NIGHTS", "number of nights should be a number between 1 and 90");
  }

  static String arrivalDateInThePast() {
    return tryWriteValueAsString("ARRIVAL_DATE_IN_THE_PAST", "cannot book a stay in the past");
  }
}
