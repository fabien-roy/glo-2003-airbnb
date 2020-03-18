package ca.ulaval.glo2003.beds.rest.exceptions;

public class ExceedingAccommodationCapacityException extends BedException {

  public ExceedingAccommodationCapacityException() {
    super("EXCEEDING_ACCOMMODATION_CAPACITY");
  }
}
