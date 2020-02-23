package ca.ulaval.glo2003.beds.helpers;

import static ca.ulaval.glo2003.beds.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import java.util.Date;
import java.util.UUID;

public class BookingBuilder {

  private BookingBuilder() {}

  private UUID DEFAULT_NUMBER = createBookingNumber();
  private UUID number = DEFAULT_NUMBER;

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private Date DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private Date arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private ca.ulaval.glo2003.beds.domain.Package DEFAULT_PACKAGE = createPackage();
  private ca.ulaval.glo2003.beds.domain.Package paquet =
      DEFAULT_PACKAGE; // package est un mot reserve...apparemment

  public static BookingBuilder aBooking() {
    return new BookingBuilder();
  }

  public BookingBuilder withNullAttributes() {
    this.number = null;
    this.tenantPublicKey = null;
    this.arrivalDate = null;
    this.numberOfNights = 0;
    this.paquet = null;
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

  public BookingBuilder withPackage(ca.ulaval.glo2003.beds.domain.Package paquet) {
    this.paquet = paquet;
    return this;
  }

  public Booking build() {
    return new Booking(
        // TODO Introduire les parametres une fois Booking implementee
        );
  }
}
