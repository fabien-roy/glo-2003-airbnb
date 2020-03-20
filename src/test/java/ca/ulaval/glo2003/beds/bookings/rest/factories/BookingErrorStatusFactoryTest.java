package ca.ulaval.glo2003.beds.bookings.rest.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.bookings.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorStatusFactory;
import java.util.stream.Stream;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BookingErrorStatusFactoryTest {

  private static BookingErrorStatusFactory bookingErrorStatusFactory;

  @BeforeAll
  public static void setUpFactory() {
    bookingErrorStatusFactory = new BookingErrorStatusFactory();
  }

  @ParameterizedTest
  @MethodSource("provideStatusForBookingException")
  public void create_withBookingException_shouldCreateAssociatedStatus(
      BookingException exception, int expectedStatus) {
    int status = bookingErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  @Test
  public void create_withException_shouldCreateDefaultStatus() {
    Exception exception = new Exception();
    int expectedStatus = new CatchallErrorStatusFactory().create(exception);

    int status = bookingErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  private static Stream<Arguments> provideStatusForBookingException() {
    return Stream.of(
        Arguments.of(new BookingNotFoundException("bookingNumber"), HttpStatus.NOT_FOUND_404),
        Arguments.of(new InvalidArrivalDateException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidNumberOfNightsException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidColonySizeException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new ExceedingResidualCapacityException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new ArrivalDateInThePastException(), HttpStatus.BAD_REQUEST_400));
  }
}
