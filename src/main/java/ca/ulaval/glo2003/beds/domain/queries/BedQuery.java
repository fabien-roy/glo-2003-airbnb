package ca.ulaval.glo2003.beds.domain.queries;

import ca.ulaval.glo2003.beds.domain.Bed;
import java.util.List;

public interface BedQuery {

  List<Bed> filter(List<Bed> beds);
}
