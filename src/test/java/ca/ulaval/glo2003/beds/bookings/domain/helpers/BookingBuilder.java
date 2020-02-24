package ca.ulaval.glo2003.beds.bookings.domain.helpers;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import java.util.Date;

public class BookingBuilder {

  private BookingBuilder() {}

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private Date DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private Date arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withTenantPublicKey(String tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
    return this;
  }

  public BookingBuilder withArrivalDate(Date arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  public BookingBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  public Booking build() {
    return new Booking(tenantPublicKey, arrivalDate, numberOfNights);
  }
}
