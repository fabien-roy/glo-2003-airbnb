package ca.ulaval.glo2003.beds.bookings.helpers;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.Booking;
import java.time.LocalDate;
import java.util.UUID;

public class BookingBuilder {

  private BookingBuilder() {}

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private LocalDate DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private LocalDate arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private String DEFAULT_BOOKING_PACKAGE = createBookingPackage();
  private String bookingPackage = DEFAULT_BOOKING_PACKAGE;

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withTenantPublicKey(String tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
    return this;
  }

  public BookingBuilder withArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  public BookingBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  public BookingBuilder withPackage(String bookingPackage) {
    this.bookingPackage = bookingPackage;
    return this;
  }

  public Booking build() {
    return new Booking(
        UUID.randomUUID(), tenantPublicKey, arrivalDate, numberOfNights, bookingPackage);
  }
}
