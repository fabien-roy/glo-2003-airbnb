package ca.ulaval.glo2003.beds.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PackagesDependencies {
  private static final Map<PackageNames, List<PackageNames>> dependencies;

  static {
    Map<PackageNames, List<PackageNames>> modifiableBaseRates = new EnumMap<>(PackageNames.class);
    modifiableBaseRates.put(PackageNames.BLOODTHIRSTY, new ArrayList<>());
    modifiableBaseRates.put(
        PackageNames.ALL_YOU_CAN_DRINK, Collections.singletonList(PackageNames.BLOODTHIRSTY));
    modifiableBaseRates.put(
        PackageNames.SWEET_TOOTH,
        Arrays.asList(PackageNames.ALL_YOU_CAN_DRINK, PackageNames.SWEET_TOOTH));
    dependencies = Collections.unmodifiableMap(modifiableBaseRates);
  }

  public static List<PackageNames> get(PackageNames packageNames) {
    return dependencies.get(packageNames);
  }
}
