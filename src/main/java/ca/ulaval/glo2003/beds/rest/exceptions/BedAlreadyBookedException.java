package ca.ulaval.glo2003.beds.rest.exceptions;

public class BedAlreadyBookedException extends RuntimeException {

  public BedAlreadyBookedException() {
    super("BED_ALREADY_BOOKED");
  }
}
