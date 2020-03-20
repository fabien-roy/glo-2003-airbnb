package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import java.util.HashMap;
import java.util.Map;

public enum Packages {
  BLOODTHIRSTY("bloodthirsty"),
  ALL_YOU_CAN_DRINK("allYouCanDrink"),
  SWEET_TOOTH("sweetTooth");

  private String name;
  private static final Map<String, Packages> lookup = new HashMap<>();

  static {
    for (Packages name : Packages.values()) {
      lookup.put(name.toString(), name);
    }
  }

  Packages(String type) {
    this.name = type;
  }

  @Override
  public String toString() {
    return name;
  }

  public static Packages get(String name) {
    Packages foundName = lookup.get(name);

    if (foundName == null) throw new InvalidPackageException();

    return foundName;
  }
}
