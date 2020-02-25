package ca.ulaval.glo2003.beds.bookings.rest.mappers;

public class BookingRequest {

  private String tenantPublicKey;
  private String arrivalDate;
  private int numberOfNights;
  private String bookingPackage;

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

  public void setTenantPublicKey(String tenantPublicKey) {
    this.tenantPublicKey = tenantPublicKey;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public void setNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
  }

  public void setBookingPackage(String bookingPackage) {
    this.bookingPackage = bookingPackage;
  }
}
