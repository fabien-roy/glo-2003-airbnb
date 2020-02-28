package ca.ulaval.glo2003.beds.bookings.rest.mappers;

public class BookingRequest {

  private String tenantPublicKey;
  private String arrivalDate;
  private int numberOfNights;
  private String bookingPackage;

  public BookingRequest() {}

  public BookingRequest(
      String tenantPublicKey, String arrivalDate, int numberOfNights, String bookingPackage) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
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

  public String getBookingPackage() {
    return bookingPackage;
  }
}
