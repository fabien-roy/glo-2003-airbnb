package ca.ulaval.glo2003.beds.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PackagesDependencies {
  private static final Map<Packages, List<Packages>> dependencies;

  static {
    Map<Packages, List<Packages>> modifiableBaseRates = new EnumMap<>(Packages.class);
    modifiableBaseRates.put(Packages.BLOODTHIRSTY, new ArrayList<>());
    modifiableBaseRates.put(
        Packages.ALL_YOU_CAN_DRINK, Collections.singletonList(Packages.BLOODTHIRSTY));
    modifiableBaseRates.put(
        Packages.SWEET_TOOTH, Collections.singletonList(Packages.ALL_YOU_CAN_DRINK));
    dependencies = Collections.unmodifiableMap(modifiableBaseRates);
  }

  public static List<Packages> get(Packages packages) {
    return dependencies.get(packages);
  }
}
