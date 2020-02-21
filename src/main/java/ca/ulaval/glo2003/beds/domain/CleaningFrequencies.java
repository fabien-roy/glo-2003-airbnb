package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCleaningFrequencyException;

import java.util.HashMap;
import java.util.Map;

public enum CleaningFrequencies {
  WEEKLY("weekly"),
  MONTHLY("monthly"),
  ANNUAL("annual"),
  NEVER("never");

  private String frequency;
  private static final Map<String, CleaningFrequencies> lookup = new HashMap<>();

  static {
    for (CleaningFrequencies frequency : CleaningFrequencies.values()) {
      lookup.put(frequency.toString(), frequency);
    }
  }

  CleaningFrequencies(String frequency) {
    this.frequency = frequency;
  }

  @Override
  public String toString() {
    return frequency;
  }

  public static CleaningFrequencies get(String frequency) {
    CleaningFrequencies foundFrequency = lookup.get(frequency);

    if (foundFrequency == null) throw new InvalidCleaningFrequencyException(); // TODO : Throw correct exception

    return foundFrequency;
  }
}
