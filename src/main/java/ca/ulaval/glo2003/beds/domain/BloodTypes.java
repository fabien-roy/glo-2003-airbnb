package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import java.util.HashMap;
import java.util.Map;

public enum BloodTypes {
  O_MINUS("O-"),
  O_PLUS("O+"),
  AB_MINUS("AB-"),
  AB_PLUS("AB+"),
  B_MINUS("B-"),
  B_PLUS("B+"),
  A_MINUS("A-"),
  A_PLUS("A+");

  private String type;
  private static final Map<String, BloodTypes> lookup = new HashMap<>();

  static {
    for (BloodTypes type : BloodTypes.values()) {
      lookup.put(type.toString(), type);
    }
  }

  BloodTypes(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

  public static BloodTypes get(String type) {
    BloodTypes foundType = lookup.get(type);

    if (foundType == null) throw new InvalidBloodTypesException();

    return foundType;
  }
}
