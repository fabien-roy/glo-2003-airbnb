package ca.ulaval.glo2003.beds.bookings.rest.factories;

import static ca.ulaval.glo2003.beds.bookings.rest.factories.BookingErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BookingErrorResponseFactoryTest {

  private static BookingErrorResponseFactory bookingErrorResponseFactory;

  @BeforeAll
  public static void setUpFactory() {
    bookingErrorResponseFactory = new BookingErrorResponseFactory();
  }

  @ParameterizedTest
  @MethodSource("provideResponseForBookingException")
  public void create_withBookingException_shouldCreateAssociatedResponse(
      BookingException exception, String expectedResponse) {
    String response = bookingErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  @Test
  public void create_withException_shouldCreateDefaultResponse() {
    Exception exception = new Exception();
    String expectedResponse = new CatchallErrorResponseFactory().create(exception);

    String response = bookingErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  private static Stream<Arguments> provideResponseForBookingException() {
    String bookingNumber = "bookingNumber";

    return Stream.of(
        Arguments.of(new BookingNotFoundException(bookingNumber), bookingNotFound(bookingNumber)),
        Arguments.of(new InvalidArrivalDateException(), invalidArrivalDate()),
        Arguments.of(new InvalidNumberOfNights(), invalidNumberOfNights()),
        Arguments.of(new ArrivalDateInThePastException(), arrivalDateInThePast()));
  }
}
