package ca.ulaval.glo2003.beds.bookings.domain.helpers;

import static ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import java.time.LocalDate;

public class BookingBuilder {

  private BookingBuilder() {}

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private LocalDate DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private LocalDate arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private PackageNames DEFAULT_PACKAGE = createPackageName();
  private PackageNames packageName = DEFAULT_PACKAGE;

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

  public BookingBuilder withPackage(PackageNames packageName) {
    this.packageName = packageName;
    return this;
  }

  public Booking build() {
    return new Booking(tenantPublicKey, arrivalDate, numberOfNights, packageName);
  }
}
