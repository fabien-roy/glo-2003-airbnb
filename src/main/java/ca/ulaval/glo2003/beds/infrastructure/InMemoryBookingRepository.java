package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.Booking;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBookingRepository {

  List<Booking> bookingList;

  public InMemoryBookingRepository() {
    bookingList = new ArrayList<>();
  }

  public void add(Booking booking) {
    bookingList.add(booking);
  }

  public List<Booking> getAll() {
    return bookingList;
  }
}
