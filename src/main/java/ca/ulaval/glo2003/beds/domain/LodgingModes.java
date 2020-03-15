package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidLodgingModeException;
import java.util.HashMap;
import java.util.Map;

public enum LodgingModes {
  PRIVATE("private"),
  COHABITATION("cohabitation");

  private String mode;
  private static final Map<String, LodgingModes> lookup = new HashMap<>();

  static {
    for (LodgingModes mode : LodgingModes.values()) {
      lookup.put(mode.toString(), mode);
    }
  }

  LodgingModes(String mode) {
    this.mode = mode;
  }

  @Override
  public String toString() {
    return mode;
  }

  public static LodgingModes get(String mode) {
    LodgingModes foundMode = lookup.get(mode);

    if (foundMode == null) throw new InvalidLodgingModeException();

    return foundMode;
  }
}
