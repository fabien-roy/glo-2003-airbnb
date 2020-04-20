package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Booking {

  private BookingNumber number;
  private PublicKey tenantPublicKey;
  private TimeDate arrivalDate;
  private int numberOfNights;
  private Integer colonySize;
  private Packages packageName;
  private Price price;
  private List<Transaction> transactions = new ArrayList<>();
  private BookingStatuses status;

  public Booking(
      PublicKey tenantPublicKey,
      TimeDate arrivalDate,
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

  public TimeDate getArrivalDate() {
    return arrivalDate;
  }

  public TimeDate getDepartureDate() {
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

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public BookingStatuses getStatus() {
    return status;
  }

  public Price getTotal() {
    return price.getTotal();
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

  public boolean isOverlapping(TimeDate otherDate) {
    return isOverlapping(otherDate, 1);
  }

  public boolean isOverlapping(TimeDate otherDate, int numberOfNights) {
    return getPeriod().isOverlapping(otherDate.periodToDays(numberOfNights - 1));
  }

  public TimePeriod getPeriod() {
    return arrivalDate.periodToDays(numberOfNights - 1);
  }

  public void setStatus(BookingStatuses bookingStatus) {
    this.status = bookingStatus;
  }
}
