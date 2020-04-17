package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Booking {

  private BookingNumber number;
  private PublicKey tenantPublicKey;
  private ReservationDate arrivalDate;
  private int numberOfNights;
  private Integer colonySize;
  private Packages packageName;
  private Price total;
  private List<Transaction> transactions = new ArrayList<>();
  private BookingStatuses status;

  public Booking(
      PublicKey tenantPublicKey,
      ReservationDate arrivalDate,
      int numberOfNights,
      Integer colonySize,
      Packages packageName) {
    this.tenantPublicKey = tenantPublicKey;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
    this.colonySize = colonySize;
    this.packageName = packageName;
  }

  public BookingNumber getNumber() {
    return number;
  }

  public void setNumber(BookingNumber number) {
    this.number = number;
  }

  public PublicKey getTenantPublicKey() {
    return tenantPublicKey;
  }

  public ReservationDate getArrivalDate() {
    return arrivalDate;
  }

  public ReservationDate getDepartureDate() {
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

  public boolean isOverlapping(ReservationDate otherDate) {
    return isOverlapping(otherDate, 1);
  }

  public boolean isOverlapping(ReservationDate otherDate, int numberOfNights) {
    return getPeriod().isOverlapping(otherDate.periodToDays(numberOfNights - 1));
  }

  public ReservationPeriod getPeriod() {
    return arrivalDate.periodToDays(numberOfNights - 1);
  }

  public void setStatus(BookingStatuses bookingStatus) {
    this.status = bookingStatus;
  }
}
