package ca.ulaval.glo2003.bookings.mappers;

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
import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingConverterTest {

  private static BookingConverter bookingConverter;
  private static BookingDateMapper bookingDateMapper = mock(BookingDateMapper.class);
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
  public static void setUpMapper() {
    bookingConverter = new BookingConverter(publicKeyConverter, bookingDateMapper, priceConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    resetMocks();
    when(publicKeyConverter.fromString(tenantPublicKey.getValue())).thenReturn(tenantPublicKey);
    when(publicKeyConverter.toString(tenantPublicKey)).thenReturn(tenantPublicKey.getValue());
    when(bookingDateMapper.fromString(arrivalDate.getValue().toString())).thenReturn(arrivalDate);
    when(bookingDateMapper.toString(arrivalDate)).thenReturn(arrivalDate.getValue().toString());
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
  public void fromRequest_shouldMapTenantPublicKey() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(tenantPublicKey, booking.getTenantPublicKey());
  }

  @Test
  public void fromRequest_shouldMapArrivalDate() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(arrivalDate, booking.getArrivalDate());
  }

  @Test
  public void fromRequest_shouldMapNumberOfNights() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(numberOfNights, booking.getNumberOfNights());
  }

  @Test
  public void fromRequest_shouldMapColonySize() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(colonySize, booking.getColonySize());
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
  public void fromRequest_shouldMapPackage() {
    booking = bookingConverter.fromRequest(bookingRequest);

    assertEquals(packageName, booking.getPackage());
  }

  @Test
  public void fromRequest_withInvalidPackage_shouldThrowInvalidPackageException() {
    packageNameValue = "invalidPackage";
    bookingRequest = buildBookingRequest();

    assertThrows(
        InvalidPackagesException.class, () -> bookingConverter.fromRequest(bookingRequest));
  }

  @Test
  public void toResponse_shouldMapArrivalDate() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(arrivalDate.getValue().toString(), bookingResponse.getArrivalDate());
  }

  @Test
  public void toResponse_shouldMapPackage() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(packageNameValue, bookingResponse.getBookingPackage());
  }

  @Test
  public void toResponse_shouldMapNumberOfNights() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(numberOfNights, bookingResponse.getNumberOfNights());
  }

  @Test
  public void toResponse_shouldMapColonySize() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(colonySize, bookingResponse.getColonySize());
  }

  @Test
  public void toResponse_shouldMapTotal() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(total.getValue().doubleValue(), bookingResponse.getTotal());
  }

  @Test
  public void toResponse_shouldMapStatus() {
    bookingResponse = bookingConverter.toResponse(booking);

    assertEquals(status.toString(), bookingResponse.getStatus());
  }
}
