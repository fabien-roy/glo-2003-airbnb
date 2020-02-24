package ca.ulaval.glo2003.beds.bookings.domain;

import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

public class Booking {

  private UUID number;
  private Base64 tenantPublicKey;
  private LocalDate arrivalDate;
  private int numberOfNights;
  private String bookingPackage;

  public Booking(
      UUID number,
      Base64 tenantPublicKey,
      LocalDate arrivalDate,
      int numberOfNights,
      String bookingPackage) {
    this.number = number;
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.bookingPackage = bookingPackage;
  }

  public float getTotal() {
    // TODO
    return 1;
  }

  public UUID getNumber() {
    return number;
  }

  public Base64 getTenantPublicKey() {
    return tenantPublicKey;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public void setNumber(UUID number) {
    this.number = number;
  }

  public String getPackage() {
    return bookingPackage;
  }

  public boolean isOverlapping(Booking otherBooking) {
    return !(arrivalDate.isAfter(otherBooking.getDepartureDate())
        || getDepartureDate().isBefore(otherBooking.getArrivalDate()));
  }

  public LocalDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights - 1);
  }
}
