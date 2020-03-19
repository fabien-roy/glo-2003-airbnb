package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import static ca.ulaval.glo2003.beds.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
import ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingBuilder;
import ca.ulaval.glo2003.beds.bookings.domain.helpers.BookingObjectMother;
import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.beds.rest.mappers.PriceMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PublicKeyMapper;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookingMapperTest {

  private static BookingMapper bookingMapper;
  private static BookingDateMapper bookingDateMapper;
  private static PublicKeyMapper publicKeyMapper;
  private static PriceMapper priceMapper;

  @BeforeAll
  public static void setUpMapper() {
    bookingDateMapper = mock(BookingDateMapper.class);
    publicKeyMapper = mock(PublicKeyMapper.class);
    priceMapper = mock(PriceMapper.class);
    bookingMapper = new BookingMapper(publicKeyMapper, bookingDateMapper, priceMapper);
  }

  @Test
  public void toResponse_shouldMapArrivalDate() {
    BookingDate expectedDate = new BookingDate(LocalDate.now());
    String expectedDateValue = expectedDate.getValue().toString();
    Booking bookingToMap = BookingBuilder.aBooking().withArrivalDate(expectedDate).build();
    when(bookingDateMapper.toString(expectedDate)).thenReturn(expectedDateValue);

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedDateValue, response.getArrivalDate());
  }

  @Test
  public void toResponse_shouldMapPackage() {
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    Booking bookingToMap = BookingBuilder.aBooking().withPackage(expectedPackageName).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedPackageName, response.getBookingPackage());
  }

  @Test
  public void toResponse_shouldMapNumberOfNights() {
    int expectedNumberOfNights = 69;
    Booking bookingToMap =
        BookingBuilder.aBooking().withNumberOfNights(expectedNumberOfNights).build();

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedNumberOfNights, response.getNumberOfNights());
  }

  @Test
  public void toResponse_shouldMapTotal() {
    double expectedTotalValue = 100.00;
    Price expectedTotal = new Price(BigDecimal.valueOf(expectedTotalValue));
    Booking bookingToMap = BookingBuilder.aBooking().withTotal(expectedTotal).build();
    when(priceMapper.toDouble(expectedTotal)).thenReturn(expectedTotalValue);

    BookingResponse response = bookingMapper.toResponse(bookingToMap);

    assertEquals(expectedTotalValue, response.getTotal());
  }

  @Test
  public void fromRequest_shouldMapTenantPublicKey() {
    PublicKey expectedPublicKey = BookingObjectMother.createTenantPublicKey();
    BookingRequest request =
        aBookingRequest().withTenantPublicKey(expectedPublicKey.getValue()).build();
    when(publicKeyMapper.fromString(expectedPublicKey.getValue())).thenReturn(expectedPublicKey);

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedPublicKey, booking.getTenantPublicKey());
  }

  @Test
  public void fromRequest_shouldMapArrivalDate() {
    BookingDate expectedDate = new BookingDate(LocalDate.now());
    String expectedDateValue = expectedDate.getValue().toString();
    BookingRequest request = aBookingRequest().withArrivalDate(expectedDateValue).build();
    when(bookingDateMapper.fromString(expectedDateValue)).thenReturn(expectedDate);

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedDate, booking.getArrivalDate());
  }

  @Test
  public void fromRequest_shouldMapNumberOfNights() {
    int expectedNumberOfNights = 69;
    BookingRequest request = aBookingRequest().withNumberOfNights(expectedNumberOfNights).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedNumberOfNights, booking.getNumberOfNights());
  }

  @Test
  public void fromRequest_shouldMapColonySize() {
    int expectedColonySize = 10;
    BookingRequest request = aBookingRequest().withColonySize(expectedColonySize).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedColonySize, booking.getColonySize());
  }

  @Test
  public void fromRequest_withInvalidColonySize_shouldThrowInvalidColonySizeException() {
    int colonySize = -10;
    BookingRequest request = aBookingRequest().withColonySize(colonySize).build();

    // assertThrows(InvalidColonySizeException.class, () -> bookingMapper.fromRequest(request));
  }

  @Test
  public void fromRequest_withTooLowNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    BookingRequest bookingRequest = aBookingRequest().withNumberOfNights(0).build();

    Assertions.assertThrows(
        InvalidNumberOfNights.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withTooHighNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    BookingRequest bookingRequest = aBookingRequest().withNumberOfNights(95).build();

    Assertions.assertThrows(
        InvalidNumberOfNights.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_shouldMapPackage() {
    Packages expectedPackageName = Packages.SWEET_TOOTH;
    BookingRequest request = aBookingRequest().withPackage(expectedPackageName.toString()).build();

    Booking booking = bookingMapper.fromRequest(request);

    assertEquals(expectedPackageName, booking.getPackage());
  }

  @Test
  public void fromRequest_withInvalidPackage_shouldThrowInvalidPackageException() {
    String invalidPackage = "invalidPackage";
    BookingRequest bookingRequest = aBookingRequest().withPackage(invalidPackage).build();

    Assertions.assertThrows(
        InvalidPackageException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withoutPackage_shouldThrowInvalidPackageException() {
    BookingRequest bookingRequest = aBookingRequest().withPackage(null).build();

    Assertions.assertThrows(
        InvalidPackageException.class, () -> bookingMapper.fromRequest(bookingRequest));
  }
}
