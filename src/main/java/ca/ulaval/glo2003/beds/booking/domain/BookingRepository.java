package ca.ulaval.glo2003.beds.booking.domain;

import java.util.List;
import java.util.UUID;

public interface BookingRepository {

  void add(Booking booking);

  List<Booking> getAll();

  Booking getByNumber(UUID number);
}
