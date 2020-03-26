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
  private int numberOfNights;

  @JsonDeserialize(using = ColonySizeDeserializer.class)
  private int colonySize;

  @JsonProperty("package")
  @JsonDeserialize(using = PackageNameDeserializer.class)
  private String bookingPackage;

  public BookingRequest() {}

  public BookingRequest(
      String tenantPublicKey,
      String arrivalDate,
      int numberOfNights,
      int colonySize,
      String bookingPackage) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.colonySize = colonySize;
    this.bookingPackage = bookingPackage;
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

  public int getColonySize() {
    return colonySize;
  }

  public String getBookingPackage() {
    return bookingPackage;
  }
}
