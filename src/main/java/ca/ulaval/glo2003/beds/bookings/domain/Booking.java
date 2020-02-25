package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Booking {

  private UUID number;
  private String tenantPublicKey;
  private LocalDate arrivalDate;
  private int numberOfNights;
  private Packages packageName; // TODO : Actually map this
  private List<Transaction> transactions;

  // TODO : Use other constructor
  public Booking(String tenantPublicKey, LocalDate arrivalDate, int numberOfNights) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
  }

  public Booking(
      String tenantPublicKey, LocalDate arrivalDate, int numberOfNights, Packages packageName) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.packageName = packageName;
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

  public Packages getPackageName() {
    return packageName;
  }

  public boolean isOverlapping(Booking otherBooking) {
    return !(arrivalDate.isAfter(otherBooking.getDepartureDate())
        || getDepartureDate().isBefore(otherBooking.getArrivalDate()));
  }

  public LocalDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights - 1);
  }
}
