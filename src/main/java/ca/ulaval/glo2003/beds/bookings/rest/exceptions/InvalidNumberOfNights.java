package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class InvalidNumberOfNights extends RuntimeException {

  public InvalidNumberOfNights() {
    super("YOU_NEED_TO_BOOK_AT_LEAST_ONE_NIGHT");
  }
}
