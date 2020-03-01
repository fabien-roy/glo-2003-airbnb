package ca.ulaval.glo2003.beds.bookings.rest;

import ca.ulaval.glo2003.beds.domain.Packages;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponse {

  private String arrivalDate;
  private int numberOfNights;

  @JsonProperty("package")
  private Packages bookingPackage;

  private float total;

  public BookingResponse(
      String arrivalDate, int numberOfNights, Packages bookingPackage, float total) {
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.bookingPackage = bookingPackage;
    this.total = total;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public Packages getBookingPackage() {
    return bookingPackage;
  }

  public float getTotal() {
    return total;
  }
}
