package ca.ulaval.glo2003.beds.domain;

import java.util.List;
import java.util.UUID;

public interface BedRepository<Q extends BedQuery> {

  void add(Bed bed);

  void update(Bed bed);

  List<Bed> getAll(Q query);

  Bed getByNumber(UUID number);
}
