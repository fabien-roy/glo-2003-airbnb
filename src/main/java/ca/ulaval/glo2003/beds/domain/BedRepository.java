package ca.ulaval.glo2003.beds.domain;

import java.util.List;
import java.util.UUID;

public interface BedRepository {

  void add(Bed bed);

  void update(Bed bed);

  List<Bed> getAll();

  List<Bed> getAll(BedQuery query);

  Bed getByNumber(UUID number);
}
