package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCleaningFrequencyFilter implements InMemoryBedFilter {

  private final CleaningFrequencies cleaningFrequency;

  public InMemoryCleaningFrequencyFilter(CleaningFrequencies cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
  }

  public CleaningFrequencies getCleaningFrequency() {
    return cleaningFrequency;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.getCleaningFrequency().equals(cleaningFrequency))
        .collect(Collectors.toList());
  }
}
