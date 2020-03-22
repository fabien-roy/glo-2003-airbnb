package ca.ulaval.glo2003.beds.infrastructure.filters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFilter;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import java.util.List;

public class InMemoryAvailabilityFilter implements BedFilter {

  private final int minCapacity;
  private final BookingDate arrivalDate;
  private final int numberOfNights;

  public InMemoryAvailabilityFilter(int minCapacity, BookingDate arrivalDate, int numberOfNights) {
    this.minCapacity = minCapacity;
    this.arrivalDate = arrivalDate;
    this.numberOfNights = numberOfNights;
  }

  public int getMinCapacity() {
    return minCapacity;
  }

  public BookingDate getArrivalDate() {
    return arrivalDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  @Override
  public List<Bed> filter(List<Bed> beds) {
    return beds; // TODO
  }
}
