package ca.ulaval.glo2003.beds.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {

  private UUID number;
  private PublicKey tenantPublicKey;
  private LocalDate arrivalDate;
  private int numberOfNights;
  private Packages packageName;
  private Price total;
  private List<Transaction> transactions = new ArrayList<>();

  public Booking(
      PublicKey tenantPublicKey, LocalDate arrivalDate, int numberOfNights, Packages packageName) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.packageName = packageName;
  }

  public UUID getNumber() {
    return number;
  }

  public void setNumber(UUID number) {
    this.number = number;
  }

  public PublicKey getTenantPublicKey() {
    return tenantPublicKey;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public Packages getPackage() {
    return packageName;
  }

  public Price getTotal() {
    return total;
  }

  public void setTotal(Price total) {
    this.total = total;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public boolean isOverlapping(Booking otherBooking) {
    return !(arrivalDate.isAfter(otherBooking.getDepartureDate())
        || getDepartureDate().isBefore(otherBooking.getArrivalDate()));
  }

  public LocalDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights - 1);
  }
}
