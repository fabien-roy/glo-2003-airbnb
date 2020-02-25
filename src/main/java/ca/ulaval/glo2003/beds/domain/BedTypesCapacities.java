package ca.ulaval.glo2003.beds.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BedTypesCapacities {
  private static final Map<BedTypes, Integer> capacities;

  private BedTypesCapacities() {}

  static {
    Map<BedTypes, Integer> modifiableBaseRates = new EnumMap<>(BedTypes.class);
    modifiableBaseRates.put(BedTypes.LATEX, 400);
    modifiableBaseRates.put(BedTypes.MEMORY_FOAM, 700);
    modifiableBaseRates.put(BedTypes.SPRINGS, 1000);
    capacities = Collections.unmodifiableMap(modifiableBaseRates);
  }

  public static Integer get(BedTypes bedType) {
    return capacities.get(bedType);
  }
}
