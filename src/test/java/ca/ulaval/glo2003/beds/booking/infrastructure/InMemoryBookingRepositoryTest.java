package ca.ulaval.glo2003.beds.booking.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.booking.domain.Booking;
import ca.ulaval.glo2003.beds.booking.domain.BookingRepository;
import ca.ulaval.glo2003.beds.booking.rest.exceptions.BookingNotFoundException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryBookingRepositoryTest {

  private BookingRepository bookingRepository;

  @BeforeEach
  public void setUpRepository() {
    bookingRepository = new InMemoryBookingRepository();
  }

  @Test
  public void add_shouldAddBooking() {
    Booking expectedBooking = mock(Booking.class);

    bookingRepository.add(expectedBooking);
    Booking actualBooking = bookingRepository.getAll().get(0);

    assertSame(expectedBooking, actualBooking);
  }

  @Test
  public void getAll_withOneBooking_shouldGetOneBooking() {
    Booking expectedBooking = mock(Booking.class);
    bookingRepository.add(expectedBooking);

    List<Booking> actualBookings = bookingRepository.getAll();

    assertEquals(1, actualBookings.size());
    assertSame(expectedBooking, actualBookings.get(0));
  }

  @Test
  public void getAll_withMultipleBookings_shouldGetMultipleBookings() {
    Booking expectedBooking = mock(Booking.class);
    Booking otherExpectedBooking = mock(Booking.class);
    bookingRepository.add(expectedBooking);
    bookingRepository.add(otherExpectedBooking);

    List<Booking> actualBookings = bookingRepository.getAll();

    assertEquals(2, actualBookings.size());
    assertTrue(actualBookings.contains(expectedBooking));
    assertTrue(actualBookings.contains(otherExpectedBooking));
  }

  @Test
  public void getByNumber_withNoBed_shouldThrowBookingNotFoundException() {
    UUID bookingNumber = mock(UUID.class);

    assertThrows(
        BookingNotFoundException.class, () -> bookingRepository.getByNumber(bookingNumber));
  }

  @Test
  public void getByNumber_withBooking_shouldGetTheBooking() {
    UUID bookingId = mock(UUID.class);
    Booking bookingToGet = mock(Booking.class);
    when(bookingToGet.getNumber()).thenReturn(bookingId);
    bookingRepository.add(bookingToGet);

    Booking actualBooking = bookingRepository.getByNumber(bookingId);
    assertSame(actualBooking, bookingToGet);
  }
}
