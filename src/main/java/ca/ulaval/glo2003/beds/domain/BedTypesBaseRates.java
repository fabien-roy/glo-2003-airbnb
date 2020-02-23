package ca.ulaval.glo2003.beds.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BedTypesBaseRates {
  private static final Map<BedTypes, Double> baseRates;

  private BedTypesBaseRates() {}

  static {
    Map<BedTypes, Double> modifiableBaseRates = new EnumMap<>(BedTypes.class);
    modifiableBaseRates.put(BedTypes.LATEX, 250.0);
    modifiableBaseRates.put(BedTypes.MEMORY_FOAM, 500.0);
    modifiableBaseRates.put(BedTypes.SPRINGS, 750.0);
    baseRates = Collections.unmodifiableMap(modifiableBaseRates);
  }

  public static Double get(BedTypes bedType) {
    return baseRates.get(bedType);
  }
}
