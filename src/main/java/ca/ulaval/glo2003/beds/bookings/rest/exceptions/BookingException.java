package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

import ca.ulaval.glo2003.beds.rest.exceptions.BedException;

public abstract class BookingException extends BedException {

  public BookingException(String message) {
    super(message);
  }
}
