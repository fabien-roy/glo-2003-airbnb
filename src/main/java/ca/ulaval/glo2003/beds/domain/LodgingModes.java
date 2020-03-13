package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidLodgingModeException;

public enum LodgingModes {
  PRIVATE("private"),
  COHABITATION("cohabitation");

  private String mode;

  LodgingModes(String mode) {
    this.mode = mode;
  }

  @Override
  public String toString() {
    return mode;
  }

  public static LodgingModes get(String mode) {
    LodgingModes foundMode;

    if (mode.equals(LodgingModes.PRIVATE.toString())) foundMode = LodgingModes.PRIVATE;
    else if (mode.equals(LodgingModes.COHABITATION.toString()))
      foundMode = LodgingModes.COHABITATION;
    else throw new InvalidLodgingModeException();

    return foundMode;
  }
}
