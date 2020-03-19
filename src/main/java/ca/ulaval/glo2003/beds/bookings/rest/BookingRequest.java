package ca.ulaval.glo2003.beds.bookings.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingRequest {

  private String tenantPublicKey;
  private String arrivalDate;
  private int numberOfNights;
  private int colonySize;

  @JsonProperty("package")
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
