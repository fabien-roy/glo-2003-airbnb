package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedNumber;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBedRepository implements BedRepository<InMemoryBedQuery> {

  private List<Bed> beds;

  public InMemoryBedRepository() {
    beds = new ArrayList<>();
  }

  @Override
  public void add(Bed bed) {
    beds.add(bed);
  }

  @Override
  public void update(Bed updatedBed) {
    Bed bed = getByNumber(updatedBed.getNumber());
    beds.set(beds.indexOf(bed), updatedBed);
  }

  @Override
  public List<Bed> getAll(InMemoryBedQuery query) {
    query.setBeds(beds);
    return query.execute();
  }

  @Override
  public Bed getByNumber(BedNumber number) {
    Optional<Bed> foundBed = beds.stream().filter(bed -> bed.getNumber().equals(number)).findAny();

    if (!foundBed.isPresent()) {
      throw new BedNotFoundException(number.toString());
    }

    return foundBed.get();
  }

  @Override
  public void deleteAll() {
    beds.clear();
  }
}
