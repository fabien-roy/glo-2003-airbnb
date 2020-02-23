package ca.ulaval.glo2003.beds.bookings.domain;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class Booking {

  private UUID number;
  private Base64 tenantPublicKey;
  private Date arrivalDate;
  private int numberOfNights;

  public double getTotal() {
    // TODO
    return 1;
  }

  public UUID getNumber() {
    return number;
  }

  public Base64 getTenantPublicKey() {
    return tenantPublicKey;
  }

  public Date getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public boolean matches(Booking otherBooking) {
    boolean result = true;
    boolean number;
    boolean tenantPublicKey;
    boolean arrivalDate;
    boolean numberOfNights;
    boolean paquet; // package est un mot reserve
    return result;
  }
}
