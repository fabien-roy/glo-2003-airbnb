package ca.ulaval.glo2003.beds.booking.rest.exceptions;

public class BookingNotFoundException extends RuntimeException {

  private final String bookingNumber;

  public BookingNotFoundException(String bookingNumber) {
    super("BOOKING_NOT_FOUND");

    this.bookingNumber = bookingNumber;
  }

  public String getBookingNumber() {
    return bookingNumber;
  }
}
