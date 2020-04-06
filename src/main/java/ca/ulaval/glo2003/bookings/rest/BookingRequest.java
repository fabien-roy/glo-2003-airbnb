package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.beds.rest.serializers.PackageNameDeserializer;
import ca.ulaval.glo2003.beds.rest.serializers.PublicKeyDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.ArrivalDateDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.ColonySizeDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.NumberOfNightsDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BookingRequest {

  private String tenantPublicKey;
  private String arrivalDate;
  private int numberOfNights = 0;
  private String bookingPackage;
  private Integer colonySize;

  public String getTenantPublicKey() {
    return tenantPublicKey;
  }

  @JsonProperty("tenantPublicKey")
  @JsonDeserialize(using = PublicKeyDeserializer.class)
  public void setTenantPublicKey(String tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  @JsonProperty("arrivalDate")
  @JsonDeserialize(using = ArrivalDateDeserializer.class)
  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  @JsonProperty("numberOfNights")
  @JsonDeserialize(using = NumberOfNightsDeserializer.class)
  public void setNumberOfNights(Integer numberOfNights) {
    if (numberOfNights != null) this.numberOfNights = numberOfNights;
  }

  public String getBookingPackage() {
    return bookingPackage;
  }

  @JsonProperty("package")
  @JsonDeserialize(using = PackageNameDeserializer.class)
  public void setBookingPackage(String bookingPackage) {
    this.bookingPackage = bookingPackage;
  }

  public Integer getColonySize() {
    return colonySize;
  }

  @JsonProperty("colonySize")
  @JsonDeserialize(using = ColonySizeDeserializer.class)
  public void setColonySize(Integer colonySize) {
    this.colonySize = colonySize;
  }
}
