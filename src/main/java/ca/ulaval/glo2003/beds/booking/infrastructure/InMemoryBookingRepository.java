package ca.ulaval.glo2003.beds.booking.infrastructure;

import ca.ulaval.glo2003.beds.booking.domain.Booking;
import ca.ulaval.glo2003.beds.booking.domain.BookingRepository;
import ca.ulaval.glo2003.beds.booking.rest.exceptions.BookingNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryBookingRepository implements BookingRepository {

  List<Booking> bookingList;

  public InMemoryBookingRepository() {
    bookingList = new ArrayList<>();
  }

  @Override
  public void add(Booking booking) {
    bookingList.add(booking);
  }

  @Override
  public List<Booking> getAll() {
    return bookingList;
  }

  @Override
  public Booking getByNumber(UUID number) {
    Optional<Booking> foundBooking =
        bookingList.stream().filter(order -> order.getNumber().equals(number)).findAny();

    if (!foundBooking.isPresent()) {
      throw new BookingNotFoundException(number.toString());
    }

    return foundBooking.get();
  }
}
