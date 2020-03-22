package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.*;
import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorResponseFactory;

public class BookingErrorResponseFactory extends CatchallErrorResponseFactory {

  @Override
  public String create(Exception exception) {
    if (exception instanceof BookingNotFoundException) {
      return bookingNotFound(((BookingNotFoundException) exception).getBookingNumber());
    } else if (exception instanceof InvalidArrivalDateException) {
      return invalidArrivalDate();
    } else if (exception instanceof InvalidNumberOfNightsException) {
      return invalidNumberOfNights();
    } else if (exception instanceof InvalidColonySizeException) {
      return invalidColonySize();
    } else if (exception instanceof ExceedingResidualCapacityException) {
      return exceedingResidualCapacityException();
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

  static String invalidColonySize() {
    return tryWriteValueAsString("INVALID_COLONY_SIZE", "colony size should be a positive number");
  }

  static String exceedingResidualCapacityException() {
    return tryWriteValueAsString(
        "EXCEEDING_RESIDUAL_CAPACITY", "colony size is exceeding residual bed capacity");
  }

  static String arrivalDateInThePast() {
    return tryWriteValueAsString("ARRIVAL_DATE_IN_THE_PAST", "cannot book a stay in the past");
  }
}
