package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public interface BedRepository<Q extends BedQuery> {

  void add(Bed bed);

  void update(Bed bed);

  List<Bed> getAll();

  List<Bed> getAll(Q query);

  Bed getByNumber(BedNumber number);
}
