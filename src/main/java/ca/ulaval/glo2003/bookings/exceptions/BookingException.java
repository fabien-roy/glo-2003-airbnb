package ca.ulaval.glo2003.bookings.exceptions;

import ca.ulaval.glo2003.beds.exceptions.BedException;

public abstract class BookingException extends BedException {

  public BookingException(String message) {
    super(message);
  }
}
