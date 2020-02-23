package ca.ulaval.glo2003.beds.bookings.rest.mappers;

public class BookingResponse {

  private String arrivalDate;
  private int numberOfNights;
  private String bookingPackage;
  private float total;

  public BookingResponse(
      String arrivalDate, int numberOfNights, String bookingPackage, float total) {
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.bookingPackage = bookingPackage;
    this.total = total;
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

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }
}
