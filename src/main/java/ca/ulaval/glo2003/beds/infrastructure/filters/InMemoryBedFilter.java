package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import java.util.List;

public interface InMemoryBedFilter {

  List<Bed> filter(List<Bed> beds);
}
