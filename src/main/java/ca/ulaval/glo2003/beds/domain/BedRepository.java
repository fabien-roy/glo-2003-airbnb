package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.domain.queries.BedQueryList;
import java.util.List;
import java.util.UUID;

public interface BedRepository {

  void add(Bed bed);

  void update(Bed bed);

  List<Bed> getAll();

  List<Bed> getAll(BedQueryList query);

  Bed getByNumber(UUID number);
}
