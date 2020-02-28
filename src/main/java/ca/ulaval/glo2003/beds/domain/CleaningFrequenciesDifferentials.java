package ca.ulaval.glo2003.beds.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class CleaningFrequenciesDifferentials {
  private static final Map<CleaningFrequencies, Double> differentials;

  private CleaningFrequenciesDifferentials() {}

  static {
    Map<CleaningFrequencies, Double> modifiableDifferentials =
        new EnumMap<>(CleaningFrequencies.class);
    modifiableDifferentials.put(CleaningFrequencies.WEEKLY, 0.5);
    modifiableDifferentials.put(CleaningFrequencies.MONTHLY, 1.0);
    modifiableDifferentials.put(CleaningFrequencies.ANNUAL, 1.25);
    modifiableDifferentials.put(CleaningFrequencies.NEVER, 2.0);
    differentials = Collections.unmodifiableMap(modifiableDifferentials);
  }

  public static Double get(CleaningFrequencies cleaningFrequency) {
    return differentials.get(cleaningFrequency);
  }
}
