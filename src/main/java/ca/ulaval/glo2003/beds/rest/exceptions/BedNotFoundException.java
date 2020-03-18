package ca.ulaval.glo2003.beds.rest.exceptions;

public class BedNotFoundException extends BedException {

  private final String bedNumber;

  public BedNotFoundException(String bedNumber) {
    super("BED_NOT_FOUND");

    this.bedNumber = bedNumber;
  }

  public String getBedNumber() {
    return bedNumber;
  }
}
