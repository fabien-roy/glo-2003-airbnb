package ca.ulaval.glo2003.bookings.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingNumber;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;

public class BookingBuilder {

  private BookingBuilder() {}

  private BookingNumber DEFAULT_BOOKING_NUMBER = createBookingNumber();
  private BookingNumber bookingNumber = DEFAULT_BOOKING_NUMBER;

  private PublicKey DEFAULT_TENANT_PUBLIC_KEY = createPublicKey();
  private PublicKey tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private TimeDate DEFAULT_ARRIVAL_DATE = aTimeDate().build();
  private TimeDate arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private Integer DEFAULT_COLONY_SIZE = createColonySize();
  private Integer colonySize = DEFAULT_COLONY_SIZE;

  private Price DEFAULT_TOTAL = createPrice();
  private Price total = DEFAULT_TOTAL;

  private Packages DEFAULT_PACKAGE = createPackageName();
  private Packages packageName = DEFAULT_PACKAGE;

  private BookingStatuses DEFAULT_BOOKING_STATUS = BookingStatuses.BOOKED;
  private BookingStatuses bookingStatus = DEFAULT_BOOKING_STATUS;

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withTenantPublicKey(PublicKey tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
    return this;
  }

  public BookingBuilder withArrivalDate(TimeDate arrivalDate) {
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
    booking.setPrice(total);
    booking.setStatus(bookingStatus);
    return booking;
  }
}
