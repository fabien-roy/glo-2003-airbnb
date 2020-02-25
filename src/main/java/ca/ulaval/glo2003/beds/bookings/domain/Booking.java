package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Booking {

  private UUID number;
  private String tenantPublicKey;
  private LocalDate arrivalDate;
  private int numberOfNights;
  private PackageNames packageNames; // TODO : Actually map this
  private List<Transaction> transactions;

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

  public PackageNames getPackageName() {
    return packageNames;
  }

  public boolean isOverlapping(Booking otherBooking) {
    return !(arrivalDate.isAfter(otherBooking.getDepartureDate())
        || getDepartureDate().isBefore(otherBooking.getArrivalDate()));
  }

  public LocalDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights - 1);
  }
}
