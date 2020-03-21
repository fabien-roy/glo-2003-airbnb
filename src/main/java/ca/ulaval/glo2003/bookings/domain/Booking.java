package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {

  private UUID number;
  private PublicKey tenantPublicKey;
  private BookingDate arrivalDate;
  private int numberOfNights;
  private int colonySize;
  private Packages packageName;
  private Price total;
  private List<Transaction> transactions = new ArrayList<>();
  private BookingStatuses status;

  public Booking(
      PublicKey tenantPublicKey,
      BookingDate arrivalDate,
      int numberOfNights,
      int colonySize,
      Packages packageName) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.colonySize = colonySize;
    this.packageName = packageName;
    this.status = BookingStatuses.BOOKED;
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

  public BookingDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public int getColonySize() {
    return colonySize;
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

  public void cancel() {
    status = BookingStatuses.CANCELED;
  }

  public boolean isOverlapping(Booking otherBooking) {
    return !(arrivalDate.getValue().isAfter(otherBooking.getDepartureDate())
        || getDepartureDate().isBefore(otherBooking.getArrivalDate().getValue()));
  }

  public LocalDate getDepartureDate() {
    return arrivalDate.getValue().plusDays(numberOfNights - 1);
  }

  public BookingStatuses getStatus() {
    return status;
  }
}
