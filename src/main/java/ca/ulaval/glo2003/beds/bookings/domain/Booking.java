package ca.ulaval.glo2003.beds.bookings.domain;

import java.util.Date;
import java.util.UUID;

public class Booking {

  private UUID number;
  private String tenantPublicKey;
  private Date arrivalDate;
  private int numberOfNights;

  public Booking(String tenantPublicKey, Date arrivalDate, int numberOfNights) {
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

  public Date getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }
}
