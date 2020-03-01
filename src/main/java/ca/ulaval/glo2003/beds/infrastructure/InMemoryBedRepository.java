package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryBedRepository implements BedRepository {

  private List<Bed> beds;

  public InMemoryBedRepository() {
    beds = new ArrayList<>();
  }

  @Override
  public void add(Bed bed) {
    beds.add(bed);
  }

  @Override
  public List<Bed> getAll() {
    return beds;
  }

  @Override
  public Bed getByNumber(UUID number) {
    Optional<Bed> foundBed = beds.stream().filter(bed -> bed.getNumber().equals(number)).findAny();

    if (!foundBed.isPresent()) {
      throw new BedNotFoundException(number.toString());
    }

    return foundBed.get();
  }
}
