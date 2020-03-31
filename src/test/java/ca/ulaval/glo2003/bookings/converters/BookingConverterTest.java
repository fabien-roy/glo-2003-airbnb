package ca.ulaval.glo2003.bookings.converters;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.converters.PublicKeyConverter;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BookingConverterTest {

  private static BookingConverter bookingConverter;
  private static BookingDateConverter bookingDateConverter = mock(BookingDateConverter.class);
  private static PublicKeyConverter publicKeyConverter = mock(PublicKeyConverter.class);
  private static PriceConverter priceConverter = mock(PriceConverter.class);

  private static Booking booking;
  private static PublicKey tenantPublicKey;
  private static BookingDate arrivalDate;
  private static int numberOfNights;
  private static int colonySize;
  private static Packages packageName;
  private static Price total;
  private static BookingStatuses status;

  private static BookingRequest bookingRequest;
  private static String packageNameValue;
  private static BookingResponse bookingResponse;

  @BeforeAll
  public static void setUpConverter() {
    bookingConverter =
        new BookingConverter(publicKeyConverter, bookingDateConverter, priceConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    resetMocks();
    when(publicKeyConverter.fromString(tenantPublicKey.getValue())).thenReturn(tenantPublicKey);
    when(publicKeyConverter.toString(tenantPublicKey)).thenReturn(tenantPublicKey.getValue());
    when(bookingDateConverter.fromString(arrivalDate.getValue().toString()))
        .thenReturn(arrivalDate);
    when(bookingDateConverter.toString(arrivalDate)).thenReturn(arrivalDate.getValue().toString());
    when(priceConverter.toDouble(total)).thenReturn(total.getValue().doubleValue());
  }

  private void resetMocks() {
    tenantPublicKey = createTenantPublicKey();
    arrivalDate = createArrivalDate();
    numberOfNights = createNumberOfNights();
    colonySize = createColonySize();
    packageName = createPackageName();
    packageNameValue = packageName.toString();
    total = createTotal();
    status = BookingStatuses.BOOKED; // TODO : Use withStatus() when it will be set in constructor

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
        .build();
  }

  private BookingRequest buildBookingRequest() {
    return aBookingRequest()
        .withTenantPublicKey(tenantPublicKey.getValue())
        .withArrivalDate(arrivalDate.getValue().toString())
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

    assertEquals(arrivalDate.getValue().toString(), bookingResponse.getArrivalDate());
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

    assertEquals(total.getValue().doubleValue(), bookingResponse.getTotal());
  }

  @Test
  public void toResponse_shouldConvertStatus() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(status.toString(), bookingResponse.getStatus());
  }
}
