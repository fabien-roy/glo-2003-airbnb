package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.beds.rest.serializers.PackageNameDeserializer;
import ca.ulaval.glo2003.beds.rest.serializers.PublicKeyDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.ArrivalDateDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.ColonySizeDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.NumberOfNightsDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BookingRequest {

  @JsonDeserialize(using = PublicKeyDeserializer.class)
  private String tenantPublicKey;

  @JsonDeserialize(using = ArrivalDateDeserializer.class)
  private String arrivalDate;

  @JsonDeserialize(using = NumberOfNightsDeserializer.class)
  private int numberOfNights = 0;

  @JsonProperty("package")
  @JsonDeserialize(using = PackageNameDeserializer.class)
  private String bookingPackage;

  @JsonDeserialize(using = ColonySizeDeserializer.class)
  private Integer colonySize;

  public BookingRequest() {
    // Empty constructor for parsing
  }

  public BookingRequest(
      String tenantPublicKey, String arrivalDate, Integer numberOfNights, String bookingPackage) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;

    if (numberOfNights != null) this.numberOfNights = numberOfNights;

    this.bookingPackage = bookingPackage;
  }

  public BookingRequest(
      String tenantPublicKey,
      String arrivalDate,
      Integer numberOfNights,
      String bookingPackage,
      Integer colonySize) {
    this(tenantPublicKey, arrivalDate, numberOfNights, bookingPackage);

    this.colonySize = colonySize;
  }

  public String getTenantPublicKey() {
    return tenantPublicKey;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public String getBookingPackage() {
    return bookingPackage;
  }

  public Integer getColonySize() {
    return colonySize;
  }
}
