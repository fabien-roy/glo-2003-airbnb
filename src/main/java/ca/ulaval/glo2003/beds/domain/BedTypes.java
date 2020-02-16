package ca.ulaval.glo2003.beds.domain;

import java.util.HashMap;
import java.util.Map;

public enum BedTypes {
  LATEX("latex"),
  MEMORY_FOAM("memoryFoam"),
  SPRINGS("springs");

  private String type;
  private static final Map<String, BedTypes> lookup = new HashMap<>();

  static {
    for (BedTypes type : BedTypes.values()) {
      lookup.put(type.toString(), type);
    }
  }

  BedTypes(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

  public static BedTypes get(String type) {
    BedTypes foundType = lookup.get(type);

    if (foundType == null) throw new RuntimeException(); // TODO : Throw correct exception

    return foundType;
  }
}
