package ca.ulaval.glo2003.beds.domain;

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
}
