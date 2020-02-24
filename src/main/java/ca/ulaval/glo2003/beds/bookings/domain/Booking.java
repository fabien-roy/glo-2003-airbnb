package ca.ulaval.glo2003.beds.bookings.domain;

import java.util.Date;
import java.util.UUID;

public class Booking {

  private UUID number;
  private String tenantPublicKey;
  private Date arrivalDate;
  private int numberOfNights;

  public double getTotal() {
    // TODO
    return 1;
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
