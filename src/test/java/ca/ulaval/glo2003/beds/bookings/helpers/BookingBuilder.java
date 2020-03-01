package ca.ulaval.glo2003.beds.bookings.helpers;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.time.LocalDate;
import java.util.UUID;

public class BookingBuilder {

  private BookingBuilder() {}

  private UUID DEFAULT_BOOKING_NUMBER = createBookingNumber();
  private UUID bookingNumber = DEFAULT_BOOKING_NUMBER;

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private LocalDate DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private LocalDate arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private Packages DEFAULT_PACKAGE = createPackageName();
  private Packages packageName = DEFAULT_PACKAGE;

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withBookingNumber(UUID bookingNumber) {
    this.bookingNumber = bookingNumber;
    return this;
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

  public BookingBuilder withPackage(Packages packageName) {
    this.packageName = packageName;
    return this;
  }

  public Booking build() {
    Booking booking = new Booking(tenantPublicKey, arrivalDate, numberOfNights, packageName);
    booking.setNumber(bookingNumber);
    return booking;
  }
}
