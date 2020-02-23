package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import java.util.Base64;

public class BookingRequest {

  private Base64 tenantPublicKey;
  private String arrivalDate;
  private int numberOfNights;
  private String bookingPackage;

  public Base64 getTenantPublicKey() {
    return tenantPublicKey;
  }

  public void setTenantPublicKey(Base64 tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public void setNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
  }

  public String getBookingPackage() {
    return bookingPackage;
  }

  public void setBookingPackage(String bookingPackage) {
    this.bookingPackage = bookingPackage;
  }
}
