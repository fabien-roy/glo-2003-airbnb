package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidBookingKeyException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingMapperTest {

  private BookingMapper bookingMapper;

  private static int VALID_NUMBER_OF_NIGHTS = 69;
  private static UUID BOOKING_ID = UUID.randomUUID();
  private static LocalDate SOME_DATE = LocalDate.now();
  private static String BOOKING_KEY = "This is the key to my heart";
  private static String BOOKING_PACKAGE = "Very good package. You won't believe your eyes";

  @BeforeEach
  public void setUpMapper() {
    bookingMapper = new BookingMapper();
  }

  @Test
  public void getBooking_shouldReturnBookingResponseWithSameParameters() {
    Booking bookingToMap =
        new Booking(BOOKING_ID, BOOKING_KEY, SOME_DATE, VALID_NUMBER_OF_NIGHTS, BOOKING_PACKAGE);

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(response.getArrivalDate(), SOME_DATE.toString());
    assertEquals(response.getBookingPackage(), BOOKING_PACKAGE);
    assertEquals(response.getNumberOfNights(), VALID_NUMBER_OF_NIGHTS);
  }

  @Test
  public void getValidBookingRequest_shouldReturnBookingWithSameParameters() {
    BookingRequest bookingRequest = setUpBookingRequest();

    Booking booking = bookingMapper.fromRequest(bookingRequest);

    assertEquals(booking.getArrivalDate(), SOME_DATE);
    assertEquals(booking.getDepartureDate(), (SOME_DATE.plusDays(VALID_NUMBER_OF_NIGHTS - 1)));
    assertEquals(booking.getNumberOfNights(), VALID_NUMBER_OF_NIGHTS);
    assertEquals(booking.getPackage(), BOOKING_PACKAGE);
    assertEquals(booking.getTenantPublicKey(), BOOKING_KEY);
  }

  @Test
  public void getRequestWithInvalidNumberOfNights_shoudThrowException() {
    Assertions.assertThrows(
        InvalidNumberOfNights.class,
        () -> {
          BookingRequest bookingRequest = setUpBookingRequest();
          when(bookingRequest.getNumberOfNights()).thenReturn(-1);

          bookingMapper.fromRequest(bookingRequest);
        });
  }

  @Test
  public void getRequestWithInvalidArrivalDate_shoudThrowException() {
    Assertions.assertThrows(
        InvalidArrivalDateException.class,
        () -> {
          BookingRequest bookingRequest = setUpBookingRequest();
          when(bookingRequest.getArrivalDate()).thenReturn("This is wrong. No Really");

          bookingMapper.fromRequest(bookingRequest);
        });
  }

  @Test
  public void getRequestWithArrivalDateInThePast_shoudThrowException() {
    Assertions.assertThrows(
        ArrivalDateInThePastException.class,
        () -> {
          BookingRequest bookingRequest = setUpBookingRequest();
          when(bookingRequest.getArrivalDate()).thenReturn("2019-07-03");

          bookingMapper.fromRequest(bookingRequest);
        });
  }

  @Test
  public void getRequestWithNoKey_shoudThrowException() {
    Assertions.assertThrows(
        InvalidBookingKeyException.class,
        () -> {
          BookingRequest bookingRequest = setUpBookingRequest();
          when(bookingRequest.getTenantPublicKey()).thenReturn(null);

          bookingMapper.fromRequest(bookingRequest);
        });
  }

  private BookingRequest setUpBookingRequest() {
    BookingRequest request = mock(BookingRequest.class);
    when(request.getNumberOfNights()).thenReturn(VALID_NUMBER_OF_NIGHTS);
    when(request.getArrivalDate()).thenReturn(SOME_DATE.toString());
    when(request.getTenantPublicKey()).thenReturn(BOOKING_KEY);
    when(request.getBookingPackage()).thenReturn(BOOKING_PACKAGE);
    return request;
  }
}
