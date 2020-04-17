package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAvailabilityFilter implements InMemoryBedFilter {

  private final int minCapacity;
  private final ReservationDate arrivalDate;
  private final int numberOfNights;

  public InMemoryAvailabilityFilter(
      int minCapacity, ReservationDate arrivalDate, int numberOfNights) {
    this.minCapacity = minCapacity;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
  }

  public int getMinCapacity() {
    return minCapacity;
  }

  public ReservationDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds.stream()
        .filter(bed -> bed.isAvailable(minCapacity, arrivalDate, numberOfNights))
        .collect(Collectors.toList());
  }
}
