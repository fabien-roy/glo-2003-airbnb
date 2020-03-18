package ca.ulaval.glo2003.beds.exceptions;

public class BedAlreadyBookedException extends BedException {

  public BedAlreadyBookedException() {
    super("BED_ALREADY_BOOKED");
  }
}
