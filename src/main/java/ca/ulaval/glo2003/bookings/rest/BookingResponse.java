package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BookingResponse {

  private String arrivalDate;
  private int numberOfNights;
  private String status;

  @JsonProperty("package")
  private String bookingPackage;

  @JsonSerialize(using = PriceSerializer.class)
  private double total;

  public BookingResponse(
      String arrivalDate, int numberOfNights, String bookingPackage, double total, String status) {
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.bookingPackage = bookingPackage;
    this.total = total;
    this.status = status;
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

  public double getTotal() {
    return total;
  }

  public String getStatus() {
    return status;
  }
}
