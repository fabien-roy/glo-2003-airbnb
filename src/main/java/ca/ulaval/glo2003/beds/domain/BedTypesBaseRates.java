package ca.ulaval.glo2003.beds.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BedTypesBaseRates {
  private static final Map<BedTypes, Double> differentials;

  private BedTypesBaseRates() {}

  static {
    Map<BedTypes, Double> modifiableDifferentials = new EnumMap<>(BedTypes.class);
    modifiableDifferentials.put(BedTypes.LATEX, 250.0);
    modifiableDifferentials.put(BedTypes.MEMORY_FOAM, 500.0);
    modifiableDifferentials.put(BedTypes.SPRINGS, 750.0);
    differentials = Collections.unmodifiableMap(modifiableDifferentials);
  }

  public static Double get(BedTypes bedType) {
    return differentials.get(bedType);
  }
}
