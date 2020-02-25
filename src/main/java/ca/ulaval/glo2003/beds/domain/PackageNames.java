package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import java.util.HashMap;
import java.util.Map;

// TODO : Rename Packages
public enum PackageNames {
  BLOODTHIRSTY("bloodthirsty"),
  ALL_YOU_CAN_DRINK("allYouCanDrink"),
  SWEET_TOOTH("sweetTooth");

  private String name;
  private static final Map<String, PackageNames> lookup = new HashMap<>();

  static {
    for (PackageNames name : PackageNames.values()) {
      lookup.put(name.toString(), name);
    }
  }

  PackageNames(String type) {
    this.name = type;
  }

  @Override
  public String toString() {
    return name;
  }

  public static PackageNames get(String name) {
    PackageNames foundName = lookup.get(name);

    if (foundName == null) throw new InvalidPackageException();

    return foundName;
  }
}
