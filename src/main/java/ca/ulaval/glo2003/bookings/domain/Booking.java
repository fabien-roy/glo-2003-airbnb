package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {

  private UUID number;
  private PublicKey tenantPublicKey;
  private BookingDate arrivalDate;
  private int numberOfNights;
  private Integer colonySize;
  private Packages packageName;
  private Price total;
  private List<Transaction> transactions = new ArrayList<>();
  private BookingStatuses status;

  public Booking(
      PublicKey tenantPublicKey,
      BookingDate arrivalDate,
      int numberOfNights,
      Integer colonySize,
      Packages packageName) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.colonySize = colonySize;
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

  public BookingDate getArrivalDate() {
    return arrivalDate;
  }

  public BookingDate getDepartureDate() {
    return arrivalDate.plusDays(numberOfNights - 1);
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public Integer getColonySize() {
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

  public BookingStatuses getStatus() {
    return status;
  }

  public void cancel() {
    status = BookingStatuses.CANCELED;
  }

  public boolean isCanceled() {
    return status.equals(BookingStatuses.CANCELED);
  }

  public boolean isOverlapping(Booking otherBooking) {
    return getPeriod().isOverlapping(otherBooking.getPeriod());
  }

  public boolean isOverlapping(BookingDate otherDate) {
    return isOverlapping(otherDate, 1);
  }

  public boolean isOverlapping(BookingDate otherDate, int numberOfNights) {
    return getPeriod().isOverlapping(otherDate.periodToDays(numberOfNights - 1));
  }

  public BookingPeriod getPeriod() {
    return arrivalDate.periodToDays(numberOfNights - 1);
  }

  public void setStatus(BookingStatuses bookingStatus) {
    this.status = bookingStatus;
  }
}
