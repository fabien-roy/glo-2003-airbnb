package ca.ulaval.glo2003.beds.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BloodTypesDifferentials {
  private static final Map<BloodTypes, Double> differentials;

  private BloodTypesDifferentials() {}

  static {
    Map<BloodTypes, Double> modifiableDifferentials = new EnumMap<>(BloodTypes.class);
    modifiableDifferentials.put(BloodTypes.O_MINUS, 1.5);
    modifiableDifferentials.put(BloodTypes.O_PLUS, 1.0);
    modifiableDifferentials.put(BloodTypes.AB_MINUS, 0.2);
    modifiableDifferentials.put(BloodTypes.AB_PLUS, 0.1);
    modifiableDifferentials.put(BloodTypes.B_MINUS, 0.5);
    modifiableDifferentials.put(BloodTypes.B_PLUS, 0.4);
    modifiableDifferentials.put(BloodTypes.A_MINUS, 0.6);
    modifiableDifferentials.put(BloodTypes.A_PLUS, 0.5);
    differentials = Collections.unmodifiableMap(modifiableDifferentials);
  }

  public static Double get(BloodTypes bloodType) {
    return differentials.get(bloodType);
  }
}
