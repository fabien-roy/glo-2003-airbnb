package ca.ulaval.glo2003.beds.domain;

import java.util.List;
import java.util.UUID;

public interface BedRepository {

  void add(Bed bed);

  List<Bed> getAll();

  Bed getByNumber(UUID number);
}
