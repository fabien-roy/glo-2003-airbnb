package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import java.util.List;
import java.util.UUID;

public class InMemoryBedRepository implements BedRepository {

  private List<Bed> beds;

  @Override
  public void add(Bed bed) {}

  @Override
  public List<Bed> getAll() {
    return beds;
  }

  @Override
  public Bed getByNumber(UUID number) {
    return new Bed();
  }
}
