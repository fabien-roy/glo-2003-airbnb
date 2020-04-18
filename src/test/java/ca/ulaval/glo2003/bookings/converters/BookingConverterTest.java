package ca.ulaval.glo2003.bookings.converters;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createColonySize;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.converters.PublicKeyConverter;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.time.converters.TimeDateConverter;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BookingConverterTest {

  private static BookingConverter bookingConverter;
  private static TimeDateConverter timeDateConverter = mock(TimeDateConverter.class);
  private static PublicKeyConverter publicKeyConverter = mock(PublicKeyConverter.class);

  private static Booking booking;
  private static PublicKey tenantPublicKey;
  private static TimeDate arrivalDate;
  private static int numberOfNights;
  private static Integer colonySize;
  private static Packages packageName;
  private static Price total;
  private static BookingStatuses status;

  private static BookingRequest bookingRequest;
  private static String packageNameValue;
  private static BookingResponse bookingResponse;

  @BeforeAll
  public static void setUpConverter() {
    bookingConverter = new BookingConverter(publicKeyConverter, timeDateConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    resetMocks();
    when(publicKeyConverter.fromString(tenantPublicKey.toString())).thenReturn(tenantPublicKey);
    when(timeDateConverter.fromString(arrivalDate.toString())).thenReturn(arrivalDate);
  }

  private void resetMocks() {
    tenantPublicKey = createPublicKey();
    arrivalDate = aTimeDate().build();
    numberOfNights = createNumberOfNights();
    colonySize = createColonySize();
    packageName = createPackageName();
    packageNameValue = packageName.toString();
    total = createPrice();
    status = BookingStatuses.BOOKED;

    booking = buildBooking();
    bookingRequest = buildBookingRequest();
  }

  private Booking buildBooking() {
    return aBooking()
        .withTenantPublicKey(tenantPublicKey)
        .withArrivalDate(arrivalDate)
        .withNumberOfNights(numberOfNights)
        .withColonySize(colonySize)
        .withPackage(packageName)
        .withTotal(total)
        .withStatus(status)
        .build();
  }

  private BookingRequest buildBookingRequest() {
    return aBookingRequest()
        .withTenantPublicKey(tenantPublicKey.toString())
        .withArrivalDate(arrivalDate.toString())
        .withNumberOfNights(numberOfNights)
        .withColonySize(colonySize)
        .withPackage(packageNameValue)
        .build();
  }

  @Test
  public void fromRequest_shouldConvertTenantPublicKey() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(tenantPublicKey, booking.getTenantPublicKey());
  }

  @Test
  public void fromRequest_shouldConvertArrivalDate() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(arrivalDate, booking.getArrivalDate());
  }

  @Test
  public void fromRequest_shouldConvertNumberOfNights() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(numberOfNights, booking.getNumberOfNights());
  }

  @Test
  public void fromRequest_shouldConvertColonySize() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(colonySize, booking.getColonySize());
  }

  @Test
  public void fromRequest_withoutColonySize_shouldNotConvertColonySize() {
    colonySize = null;
    bookingRequest = buildBookingRequest();

    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(colonySize, booking.getColonySize());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, -1})
  public void fromRequest_withNonPositiveColonySize_shouldThrowInvalidColonySizeException(
      int invalidColonySize) {
    colonySize = invalidColonySize;
    bookingRequest = buildBookingRequest();

    assertThrows(
        InvalidColonySizeException.class, () -> bookingConverter.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withTooLowNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    numberOfNights = 0;
    bookingRequest = buildBookingRequest();

    assertThrows(
        InvalidNumberOfNightsException.class, () -> bookingConverter.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_withTooHighNumberOfNights_shouldThrowInvalidNumberOfNightsException() {
    numberOfNights = 95;
    bookingRequest = buildBookingRequest();

    assertThrows(
        InvalidNumberOfNightsException.class, () -> bookingConverter.fromRequest(bookingRequest));
  }

  @Test
  public void fromRequest_shouldConvertPackage() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(packageName, booking.getPackage());
  }

  @Test
  public void fromRequest_withInvalidPackage_shouldThrowInvalidPackageException() {
    packageNameValue = "invalidPackage";
    bookingRequest = buildBookingRequest();

    assertThrows(InvalidPackageException.class, () -> bookingConverter.fromRequest(bookingRequest));
  }

  @Test
  public void toResponse_shouldConvertArrivalDate() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(arrivalDate.toString(), bookingResponse.getArrivalDate());
  }

  @Test
  public void toResponse_shouldConvertPackage() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(packageNameValue, bookingResponse.getBookingPackage());
  }

  @Test
  public void toResponse_shouldConvertNumberOfNights() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(numberOfNights, bookingResponse.getNumberOfNights());
  }

  @Test
  public void toResponse_shouldConvertTotal() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(total.toDouble(), bookingResponse.getTotal());
  }

  @Test
  public void toResponse_shouldConvertStatus() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(status.toString(), bookingResponse.getStatus());
  }
}
