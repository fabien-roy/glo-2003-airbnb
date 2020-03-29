package ca.ulaval.glo2003.bookings.rest.helpers;

import static ca.ulaval.glo2003.bookings.rest.helpers.BookingResponseObjectMother.*;

import ca.ulaval.glo2003.bookings.rest.BookingResponse;

public class BookingResponseBuilder {

  private BookingResponseBuilder() {}

  private String DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private String arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private int DEFAULT_COLONY_SIZE = createColonySize();
  private int colonySize = DEFAULT_COLONY_SIZE;

  private String DEFAULT_BOOKING_PACKAGE = createBookingPackage();
  private String bookingPackage = DEFAULT_BOOKING_PACKAGE;

  private double DEFAULT_TOTAL = createTotal();
  private double total = DEFAULT_TOTAL;

  private String DEFAULT_STATUS = createStatus();
  private String status = DEFAULT_STATUS;

  public static BookingResponseBuilder aBookingResponse() {
    return new BookingResponseBuilder();
  }

  public BookingResponse build() {
    return new BookingResponse(
        arrivalDate, numberOfNights, colonySize, bookingPackage, total, status);
  }
}
