package ca.ulaval.glo2003.beds.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.domain.Booking;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryBookingRepositoryTest {

  private InMemoryBookingRepository inMemoryBookingRepository;

  @BeforeEach
  public void setUpRepository() {
    inMemoryBookingRepository = new InMemoryBookingRepository();
  }

  @Test
  public void add_shouldAddBooking() {
    Booking expectedBooking = mock(Booking.class);

    inMemoryBookingRepository.add(expectedBooking);
    Booking actualBooking = inMemoryBookingRepository.getAll().get(0);

    assertSame(expectedBooking, actualBooking);
  }

  @Test
  public void getAll_withOneBooking_shouldGetOneBooking() {
    Booking expectedBooking = mock(Booking.class);
    inMemoryBookingRepository.add(expectedBooking);

    List<Booking> actualBookings = inMemoryBookingRepository.getAll();

    assertEquals(1, actualBookings.size());
    assertSame(expectedBooking, actualBookings.get(0));
  }

  @Test
  public void getAll_withMultipleBookings_shouldGetMultipleBookings() {
    Booking expectedBooking = mock(Booking.class);
    Booking otherExpectedBooking = mock(Booking.class);
    inMemoryBookingRepository.add(expectedBooking);
    inMemoryBookingRepository.add(otherExpectedBooking);

    List<Booking> actualBookings = inMemoryBookingRepository.getAll();

    assertEquals(2, actualBookings.size());
    assertTrue(actualBookings.contains(expectedBooking));
    assertTrue(actualBookings.contains(otherExpectedBooking));
  }
}
