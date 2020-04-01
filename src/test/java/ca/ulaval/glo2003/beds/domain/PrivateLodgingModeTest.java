package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createCapacity;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createArrivalDate;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrivateLodgingModeTest {

  private static LodgingMode privateLodgingMode;
  private static Bed bed = mock(Bed.class);
  private static Booking booking;
  private static Integer minCapacity;
  private static BookingDate arrivalDate;
  private static int numberOfNights;
  private static List<Booking> bookings;

  @BeforeAll
  public static void setUpLodgingMode() {
    privateLodgingMode = new PrivateLodgingMode();
  }

  @BeforeEach
  public void setUpMocks() {
    minCapacity = createCapacity();
    arrivalDate = createArrivalDate();
    numberOfNights = createNumberOfNights();

    resetMocks();
  }

  private void resetMocks() {
    booking = buildBooking();
    bookings = Collections.emptyList();
    resetBed();
  }

  private void resetBed() {
    reset(bed);
    when(bed.getBookings()).thenReturn(bookings);
  }

  private Booking buildBooking() {
    return aBooking()
        .withColonySize(minCapacity)
        .withArrivalDate(arrivalDate)
        .withNumberOfNights(numberOfNights)
        .build();
  }

  @Test
  public void isAvailable_withNoBooking_shouldReturnTrue() {
    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertTrue(result);
  }

  @Test
  public void isAvailable_withSameDayBooking_shouldReturnFalse() {
    bookings = Collections.singletonList(booking);
    resetBed();

    boolean result = privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate, numberOfNights);

    assertFalse(result);
  }

  @Test
  public void isAvailable_withDifferentDayBooking_shouldReturnTrue() {
    bookings = Collections.singletonList(booking);
    numberOfNights = 1;
    resetMocks();

    boolean result =
        privateLodgingMode.isAvailable(bed, minCapacity, arrivalDate.plusDays(1), numberOfNights);

    assertTrue(result);
  }

  @Test
  public void getName_shouldReturnPrivate() {
    assertEquals(LodgingModes.PRIVATE, privateLodgingMode.getName());
  }
}
