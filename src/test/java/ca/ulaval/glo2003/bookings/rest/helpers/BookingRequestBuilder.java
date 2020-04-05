package ca.ulaval.glo2003.bookings.rest.helpers;

import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestObjectMother.*;

import ca.ulaval.glo2003.bookings.rest.BookingRequest;

public class BookingRequestBuilder {

  private BookingRequestBuilder() {}

  private String DEFAULT_TENANT_PUBLIC_KEY = createTenantPublicKey();
  private String tenantPublicKey = DEFAULT_TENANT_PUBLIC_KEY;

  private String DEFAULT_ARRIVAL_DATE = createArrivalDate();
  private String arrivalDate = DEFAULT_ARRIVAL_DATE;

  private int DEFAULT_NUMBER_OF_NIGHTS = createNumberOfNights();
  private int numberOfNights = DEFAULT_NUMBER_OF_NIGHTS;

  private Integer DEFAULT_COLONY_SIZE = createColonySize();
  private Integer colonySize = DEFAULT_COLONY_SIZE;

  private String DEFAULT_PACKAGE = createPackageName();
  private String packageName = DEFAULT_PACKAGE;

  public static BookingRequestBuilder aBookingRequest() {
    return new BookingRequestBuilder();
  }

  public BookingRequestBuilder withTenantPublicKey(String tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
    return this;
  }

  public BookingRequestBuilder withArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  public BookingRequestBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  public BookingRequestBuilder withColonySize(Integer colonySize) {
    this.colonySize = colonySize;
    return this;
  }

  public BookingRequestBuilder withPackage(String packageName) {
    this.packageName = packageName;
    return this;
  }

  public BookingRequest build() {
    return new BookingRequest(
        tenantPublicKey, arrivalDate, numberOfNights, packageName, colonySize);
  }
}
