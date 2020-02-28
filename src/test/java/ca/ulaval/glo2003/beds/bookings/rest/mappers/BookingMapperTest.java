package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingRequestBuilder.aBookingRequest;
import static ca.ulaval.glo2003.beds.bookings.helpers.BookingRequestObjectMother.createTenantPublicKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
  public void toResponse_shouldReturnBookingResponseWithSameDate() {
    LocalDate expectedDate = LocalDate.now();
    Booking bookingToMap = BookingBuilder.aBooking().withArrivalDate(expectedDate).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedDate.toString(), response.getArrivalDate());
  }

  @Test
  public void toResponse_shouldReturnBookingResponseWithSameBookingPackage() {
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    Booking bookingToMap = BookingBuilder.aBooking().withPackage(expectedPackageName).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedPackageName, response.getBookingPackage());
  }

  @Test
  public void toResponse_shouldReturnBookingResponseWithSameParameters() {
    int expectedNumberOfNights = 69;
    Booking bookingToMap =
        BookingBuilder.aBooking().withNumberOfNights(expectedNumberOfNights).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedNumberOfNights, response.getNumberOfNights());
  }

  @Test
  public void fromRequest_shouldMapArrivalDate() {
    LocalDate expectedDate = LocalDate.now();
    BookingRequest request = aBookingRequest().withArrivalDate(expectedDate.toString()).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedDate, booking.getArrivalDate());
  }

  @Test
  public void fromRequest_shouldMapPackage() {
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    BookingRequest request = aBookingRequest().withPackage(expectedPackageName.toString()).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedPackageName, booking.getPackage());
  }

  @Test
  public void fromRequest_shouldMapPublicKey() {
    String expectedPublicKey = createTenantPublicKey();
    BookingRequest request = aBookingRequest().withTenantPublicKey(expectedPublicKey).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedPublicKey, booking.getTenantPublicKey());
  }

  @Test
  public void fromRequest_shouldMapNumberOfNights() {
    int expectedNumberOfNights = 69;
    BookingRequest request = aBookingRequest().withNumberOfNights(expectedNumberOfNights).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedNumberOfNights, booking.getNumberOfNights());
  }

  @Test
  public void fromRequest_withInvalidNumberOfNights_shoudThrowInvalidNumberOfNights() {
    BookingRequest bookingRequest = aBookingRequest().withNumberOfNights(95).build();
    Assertions.assertThrows(
        InvalidNumberOfNights.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withInvalidArrivalDate_shoudThrowInvalidArrivalDate() {
    BookingRequest bookingRequest =
        aBookingRequest().withArrivalDate("This is wrong. No Really").build();
    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withArrivalDateInThePast_shoudThrowArrivalDateInThePast() {
    BookingRequest bookingRequest = aBookingRequest().withArrivalDate("2019-07-03").build();
    Assertions.assertThrows(
        ArrivalDateInThePastException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withoutTenantPublicKey_shoudThrowInvalidPublicKeyException() {
    BookingRequest bookingRequest =
        aBookingRequest().withTenantPublicKey("key that is too short").build();
    Assertions.assertThrows(
        InvalidPublicKeyException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }
}
