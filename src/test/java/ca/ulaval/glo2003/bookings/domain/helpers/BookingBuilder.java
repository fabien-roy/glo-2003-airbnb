package ca.ulaval.glo2003.bookings.domain.helpers;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.UUID;

public class BookingBuilder {

  private BookingBuilder() {}

  private UUID DEFAULT_BOOKING_NUMBER = createBookingNumber();
  private UUID bookingNumber = DEFAULT_BOOKING_NUMBER;

  private PublicKey DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private PublicKey tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private BookingDate DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private BookingDate arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private Integer DEFAULT_COLONY_SIZE = createColonySize();
  private Integer colonySize = DEFAULT_COLONY_SIZE;

  private Price DEFAULT_TOTAL = createTotal();
  private Price total = DEFAULT_TOTAL;

  private Packages DEFAULT_PACKAGE = createPackageName();
  private Packages packageName = DEFAULT_PACKAGE;

  private BookingStatuses bookingStatus = BookingStatuses.BOOKED;

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withTenantPublicKey(PublicKey tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
    return this;
  }

  public BookingBuilder withArrivalDate(BookingDate arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  public BookingBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  public BookingBuilder withColonySize(Integer colonySize) {
    this.colonySize = colonySize;
    return this;
  }

  public BookingBuilder withPackage(Packages packageName) {
    this.packageName = packageName;
    return this;
  }

  public BookingBuilder withTotal(Price total) {
    this.total = total;
    return this;
  }

  public BookingBuilder withStatus(BookingStatuses bookingStatus) {
    this.bookingStatus = bookingStatus;
    return this;
  }

  public Booking build() {
    Booking booking =
        new Booking(tenantPublicKey, arrivalDate, numberOfNights, colonySize, packageName);
    booking.setNumber(bookingNumber);
    booking.setTotal(total);
    booking.setStatus(bookingStatus);
    return booking;
  }
}
