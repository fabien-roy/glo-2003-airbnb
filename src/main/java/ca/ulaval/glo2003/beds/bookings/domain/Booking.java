package ca.ulaval.glo2003.beds.bookings.domain;


import java.time.LocalDate;
import java.util.UUID;

public class Booking {

  private UUID number;
  private String tenantPublicKey;
  private LocalDate arrivalDate;
  private int numberOfNights;

  public Booking(String tenantPublicKey, LocalDate arrivalDate, int numberOfNights) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
  }

  public UUID getNumber() {
    return number;
  }

  public String getTenantPublicKey() {
    return tenantPublicKey;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  // TODO : Test
  public LocalDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights);
  }

  // TODO : Move tests into Booking
  public boolean isOverlapping(Booking booking) {
    return !(arrivalDate.isAfter(booking.getDepartureDate())
        || getDepartureDate().isBefore(booking.getArrivalDate()));
  }
}
