package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.helpers.BookingBuilder;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.InvalidPublicKeyException;
import ca.ulaval.glo2003.beds.domain.Packages;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingMapperTest {

  private BookingMapper bookingMapper;

  @BeforeEach
  public void setUpMapper() {
    bookingMapper = new BookingMapper();
  }

  @Test
  public void getBookingWithValidDate_shouldReturnBookingResponseWithSameDate() {
    LocalDate expectedDate = LocalDate.now();
    Booking bookingToMap = BookingBuilder.aBooking().withArrivalDate(expectedDate).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(response.getArrivalDate(), expectedDate.toString());
  }

  @Test
  public void getBookingWithBookingPackage_shouldReturnBookingResponseWithSameBookingPackage() {
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    Booking bookingToMap = BookingBuilder.aBooking().withPackage(expectedPackageName).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(response.getBookingPackage(), expectedPackageName);
  }

  @Test
  public void getBooking_shouldReturnBookingResponseWithSameParameters() {
    int expectedNumberOfNights = 69;
    Booking bookingToMap =
        BookingBuilder.aBooking().withNumberOfNights(expectedNumberOfNights).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(response.getNumberOfNights(), expectedNumberOfNights);
  }

  @Test
  public void getValidBookingRequest_shouldReturnBookingWithSameArrivalDate() {
    BookingRequest request = setUpBookingRequest();
    LocalDate expectedDate = LocalDate.now();
    when(request.getArrivalDate()).thenReturn(expectedDate.toString());

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(booking.getArrivalDate(), expectedDate);
  }

  @Test
  public void getValidBookingRequest_shouldReturnBookingWithSamePackage() {
    BookingRequest request = setUpBookingRequest();
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    when(request.getBookingPackage()).thenReturn(expectedPackageName.toString());

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(booking.getPackageName(), expectedPackageName);
  }

  @Test
  public void getValidBookingRequest_shouldReturnBookingWithSamePublicKey() {
    BookingRequest request = setUpBookingRequest();
    String expectedPublicKey = "this is a key";
    when(request.getTenantPublicKey()).thenReturn(expectedPublicKey);

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(booking.getTenantPublicKey(), expectedPublicKey);
  }

  @Test
  public void getValidBookingRequest_shouldReturnBookingWithSameNumberOfNights() {
    BookingRequest request = setUpBookingRequest();
    int expectedNumberOfNights = 1969;
    when(request.getNumberOfNights()).thenReturn(expectedNumberOfNights);

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(booking.getNumberOfNights(), expectedNumberOfNights);
  }

  @Test
  public void getRequestWithInvalidNumberOfNights_shoudThrowException() {
    BookingRequest bookingRequest = setUpBookingRequest();
    when(bookingRequest.getNumberOfNights()).thenReturn(-1);
    Assertions.assertThrows(
        InvalidNumberOfNights.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void getRequestWithInvalidArrivalDate_shoudThrowException() {
    BookingRequest bookingRequest = setUpBookingRequest();
    when(bookingRequest.getArrivalDate()).thenReturn("This is wrong. No Really");
    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void getRequestWithArrivalDateInThePast_shoudThrowException() {
    BookingRequest bookingRequest = setUpBookingRequest();
    when(bookingRequest.getArrivalDate()).thenReturn("2019-07-03");
    Assertions.assertThrows(
        ArrivalDateInThePastException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void getRequestWithNoKey_shoudThrowException() {
    BookingRequest bookingRequest = setUpBookingRequest();
    when(bookingRequest.getTenantPublicKey()).thenReturn(null);
    Assertions.assertThrows(
        InvalidPublicKeyException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  private BookingRequest setUpBookingRequest() {
    BookingRequest request = mock(BookingRequest.class);
    when(request.getNumberOfNights()).thenReturn(69);
    when(request.getArrivalDate()).thenReturn(LocalDate.now().toString());
    when(request.getTenantPublicKey()).thenReturn("a key");
    when(request.getBookingPackage()).thenReturn(Packages.BLOODTHIRSTY.toString());
    return request;
  }
}
