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

  @JsonDeserialize(using = ColonySizeDeserializer.class)
  private Integer colonySize;

  @JsonProperty("package")
  @JsonDeserialize(using = PackageNameDeserializer.class)
  private String bookingPackage;

  public BookingRequest() {
    // Empty constructor for parsing
  }

  public BookingRequest(
      String tenantPublicKey,
      String arrivalDate,
      Integer numberOfNights,
      Integer colonySize,
      String bookingPackage) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;

    if (numberOfNights != null) this.numberOfNights = numberOfNights;

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

  public Integer getColonySize() {
    return colonySize;
  }

  public String getBookingPackage() {
    return bookingPackage;
  }
}
